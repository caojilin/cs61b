import java.lang.Math;

public class Planet {
    String imgFileName;
    double mass;
    double xxPos;
    double xxVel;
    double yyPos;
    double yyVel;

    private static final double gravi_constant = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return (gravi_constant * mass * p.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double dx = p.xxPos - xxPos;
        return force * dx / calcDistance(p);

    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double dy = p.yyPos - yyPos;
        return force * dy / calcDistance(p);

    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netforce = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            netforce = netforce + calcForceExertedByX(p);
        }
        return netforce;

    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netforce = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            netforce = netforce + calcForceExertedByY(p);
        }
        return netforce;
    }

    public void update(double dt, double fX, double fY) {
        double accele_x = fX / mass;
        double accele_y = fY / mass;
        this.xxVel = xxVel + accele_x * dt;
        this.yyVel = yyVel + accele_y * dt;
        this.xxPos = xxPos + dt * xxVel;
        this.yyPos = yyPos + dt * yyVel;

    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();

    }


}