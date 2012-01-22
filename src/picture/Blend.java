package picture;

public class Blend {

    private Picture[] pics;

    public Blend(Picture[] pics) {
	this.pics = pics;
    }

    public Picture blend() {
	int minW = Integer.MAX_VALUE;
	int minH = Integer.MAX_VALUE;

	for (Picture p : pics) {
	    if (p.getWidth() < minW) {
		minW = p.getWidth();
	    }

	    if (p.getHeight() < minH) {
		minH = p.getHeight();
	    }
	}

	Picture temp = Utils.createPicture(minW, minH);

	for (int i = 0; i < minW; i++) {
	    for (int j = 0; j < minH; j++) {
		int r = 0;
		int g = 0;
		int b = 0;

		for (Picture p : pics) {
		    r += p.getPixel(i, j).getRed();
		    g += p.getPixel(i, j).getGreen();
		    b += p.getPixel(i, j).getBlue();
		}

		r /= pics.length;
		g /= pics.length;
		b /= pics.length;
		Color c = new Color(r, g, b);
		temp.setPixel(i, j, c);
	    }
	}
	return temp;
    }

}