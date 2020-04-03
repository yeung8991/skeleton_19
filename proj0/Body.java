public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
                    double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double squareDistance = Math.pow((b.xxPos - this.xxPos), 2)
                            + Math.pow((b.yyPos - this.yyPos), 2);
        return Math.sqrt(squareDistance);
    }

    public double calcForceExertedBy(Body b) {
        return Body.G * b.mass * this.mass / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodys) {
        double xForce = 0;
        for (Body b: bodys) {
            if (this.equals(b))
                continue;
            xForce += this.calcForceExertedByX(b);
        }
        return xForce;
    }

    public double calcNetForceExertedByY(Body[] bodys) {
        double yForce = 0;
        for (Body b: bodys) {
            if (this.equals(b))
                continue;
            yForce += this.calcForceExertedByY(b);
        }
        return yForce;
    }

    public void update(double time, double xForce, double yForce) {
        this.xxVel += time * xForce / this.mass;
        this.yyVel += time * yForce / this.mass;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
