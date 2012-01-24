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

    public Picture rotate(Picture p, int angle) {
	if (angle > 0) {
	    return rotate(rotateNinety(p), angle - 90);
	} else {
	    return p;
	}
    }

    private Picture rotateNinety(Picture p) {
	int pW = p.getWidth();
	int pH = p.getHeight();
	Picture temp = Utils.createPicture(pH, pW);

	for (int i = 0; i < pW; i++) {
	    for (int j = 0; j < pH; j++) {
		temp.setPixel(pH - 1 - j, i, p.getPixel(i, j));
	    }
	}

	return temp;
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