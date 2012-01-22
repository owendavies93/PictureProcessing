package picture;

public class Process {

    private Picture pic;
    private int h;
    private int w;

    public Process(Picture pic) {
	this.pic = pic;
	this.h = this.pic.getHeight();
	this.w = this.pic.getWidth();
    }

    public void invert() {
	for (int i = 0; i < h; i++) {
	    for (int j = 0; j < w; j++) {
		int r = 255 - pic.getPixel(j, i).getRed();
		int g = 255 - pic.getPixel(j, i).getGreen();
		int b = 255 - pic.getPixel(j, i).getBlue();
		pic.setPixel(j, i, new Color(r, g, b));
	    }
	}
    }

    public void grayscale() {
	for (int i = 0; i < h; i++) {
	    for (int j = 0; j < w; j++) {
		int c = (pic.getPixel(j, i).getRed()
			+ pic.getPixel(j, i).getGreen() + pic.getPixel(j, i)
			.getBlue()) / 3;
		pic.setPixel(j, i, new Color(c, c, c));
	    }
	}
    }

    public Picture rotate(int angle) {
	double deg2rad = Math.toRadians(angle);
	int nW = (int) (Math.abs((h * Math.sin(deg2rad))) + Math.abs((w * Math
		.cos(deg2rad))));
	int nH = (int) (Math.abs((h * Math.cos(deg2rad))) + Math.abs((w * Math
		.sin(deg2rad))));
	Picture temp = Utils.createPicture(nW, nH);

	double hw = ((double) w - 1) / 2;
	double hh = ((double) h - 1) / 2;

	for (int i = 0; i < nW; i++) {
	    for (int j = 0; j < nH; j++) {
		double[][] nc = rotateCs(new double[][] { { i - hw },
			{ j - hh } }, deg2rad);
		try {
		    temp.setPixel((int) (nc[0][0] + hw), (int) (nc[1][0] + hh),
			    pic.getPixel(i, j));

		} catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println(i + ", " + j);
		}
	    }
	}
	return temp;
    }

    public double[][] rotateCs(double[][] cs, double angle) {
	double[][] rotateM = matrixMultiply(
		new double[][] { { Math.cos(angle), -Math.sin(angle) },
			{ Math.sin(angle), Math.cos(angle) } }, cs);
	double[][] res = { { (double) Math.round(rotateM[0][0] * 10) / 10 },
		{ (double) Math.round(rotateM[1][0] * 10) / 10 } };
	return res;
    }

    private double[][] matrixMultiply(double[][] m, double[][] n) {
	double[][] res = new double[m.length][n[0].length];
	for (int i = 0; i < res.length; i++) {
	    for (int k = 0; k < res[0].length; k++) {
		for (int j = 0; j < n.length; j++) {
		    res[i][k] += (m[i][j] * n[j][k]);
		}
	    }
	}
	return res;
    }

    public Picture flip(String dir) {
	Picture temp = Utils.createPicture(w, h);
	if (dir.equals("H")) {
	    for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
		    temp.setPixel((w - 1) - i, j, pic.getPixel(i, j));
		}
	    }
	} else if (dir.equals("V")) {
	    for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
		    temp.setPixel(j, (h - 1) - i, pic.getPixel(j, i));
		}
	    }
	}
	return temp;
    }

    public Picture blur() {
	Picture temp = Utils.createPicture(w, h);
	for (int i = 0; i < w; i++) {
	    for (int j = 0; j < h; j++) {
		if (i % (w - 1) == 0 || j % (h - 1) == 0) {
		    temp.setPixel(i, j, pic.getPixel(i, j));
		} else {
		    temp.setPixel(i, j, average(i, j));
		}
	    }
	}
	return temp;
    }

    private Color average(int x, int y) {
	int r = 0;
	int g = 0;
	int b = 0;

	for (int i = x - 1; i <= x + 1; i++) {
	    for (int j = y - 1; j <= y + 1; j++) {
		try {
		    r += pic.getPixel(i, j).getRed();
		    g += pic.getPixel(i, j).getGreen();
		    b += pic.getPixel(i, j).getBlue();

		} catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println(i + ", " + j);
		}
	    }
	}

	r /= 9;
	g /= 9;
	b /= 9;

	return new Color(r, g, b);
    }
}