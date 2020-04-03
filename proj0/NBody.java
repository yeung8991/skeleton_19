public class NBody {
    public static double readRadius(String route) {
        In in = new In(route);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String route) {
        In in = new In(route);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[num];

        int count = 0;
        while (!in.isEmpty() && count < num) {
            double xPos = in.readDouble(), yPos = in.readDouble(),
                    xVel = in.readDouble(), yVel = in.readDouble(),
                    mass = in.readDouble();
            String img = in.readString();
            bodies[count] = new Body(xPos, yPos, xVel, yVel, mass, img);
            count += 1;
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allBodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();

        /** Set up the initial status of the universe. */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Body b: allBodies) {
            b.draw();
        }

        /** Begin the loop.   */
        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];

            /** Calculate the net force of each body. */
            int count = 0;
            for (Body b: allBodies) {
                xForces[count] = b.calcNetForceExertedByX(allBodies);
                yForces[count] = b.calcNetForceExertedByY(allBodies);
                count += 1;
            }

            /** Update the position of each body and draw it.  */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            count = 0;
            for (Body b: allBodies) {
                b.update(dt, xForces[count], yForces[count]);
                b.draw();
                count += 1;
            }

            /** Show it.   */
            StdDraw.show();
            StdDraw.pause(10);
        }

        /** Print the universe. */
        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                          allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }
    }
}
