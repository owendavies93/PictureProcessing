package picture;

public class Main {

    public static void main(String[] args) {

	if (args[0].equals("invert")) {

	    Picture p = Utils.loadImage(args[1]);
	    Process pr = new Process(p);
	    pr.invert();
	    Utils.savePicture(p, args[2]);

	} else if (args[0].equals("grayscale")) {

	    Picture p = Utils.loadImage(args[1]);
	    Process pr = new Process(p);
	    pr.grayscale();
	    Utils.savePicture(p, args[2]);

	} else if (args[0].equals("rotate")) {

	    Picture p = Utils.loadImage(args[2]);
	    Process pr = new Process(p);
	    Utils.savePicture(pr.rotate(p, Integer.parseInt(args[1])), args[3]);

	} else if (args[0].equals("flip")) {

	    Picture p = Utils.loadImage(args[2]);
	    Process pr = new Process(p);
	    Utils.savePicture(pr.flip(args[1]), args[3]);

	} else if (args[0].equals("blend")) {

	    int count = args.length - 2;
	    Picture[] ps = new Picture[count];
	    for (int i = 1; i <= count; i++) {
		ps[i - 1] = Utils.loadImage(args[i]);
	    }
	    Blend b = new Blend(ps);
	    Utils.savePicture(b.blend(), args[args.length - 1]);

	} else if (args[0].equals("blur")) {

	    Picture p = Utils.loadImage(args[1]);
	    Process pr = new Process(p);
	    Utils.savePicture(pr.blur(), args[2]);

	}
    }
}