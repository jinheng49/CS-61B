
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double G =6.67e-11;

    public Planet(double xP, double yP,double xV,double yV,double m,String img){
        xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
    }
    public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)
        + (yyPos - p.yyPos)*(yyPos - p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        double r=calcDistance(p);
        return G * mass * p.mass/(r*r);

    }
    public double calcForceExertedByX(Planet p){
        double F=calcForceExertedBy(p);
        double r=calcDistance(p);
        return F * (p.xxPos-xxPos)/r;
    }
    public double calcForceExertedByY(Planet p){
        double F=calcForceExertedBy(p);
        double r=calcDistance(p);
        return F * (p.yyPos-yyPos)/r;
    }
    public double calcNetForceExertedByX(Planet allPlanets[]){
        double netforce=0;
        for(int i=0;i<allPlanets.length;i=i+1){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netforce+=this.calcForceExertedByX(allPlanets[i]);
        }
        return netforce;
    }
        public double calcNetForceExertedByY(Planet allPlanets[]){
        double netforce=0;
        for(int i=0;i<allPlanets.length;i=i+1){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netforce+=this.calcForceExertedByY(allPlanets[i]);
        }
        return netforce;
    }
        public void update(double dt,double fX,double fY){
            double aXX=fX/mass;
            xxVel=xxVel+aXX*dt;
            xxPos=xxPos+xxVel*dt;

            double aYY=fY/mass;
            yyVel=yyVel+aYY*dt;
            yyPos=yyPos+yyVel*dt;

        }

        public void draw(){
            StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        }

}
