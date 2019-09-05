package AsteroidsGame.utilities;

// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        x = 0;
        y = 0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if(o == this) return true;
        if (!(o instanceof Vector2D)) return false;
        Vector2D other = (Vector2D) o;
        return other.y == this.y && other.x == this.x;
    }

    // String for displaying vector as text
    public String toString() {
        return "The vector has coordinates (" + this.x + "," + this.y + ").";
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.sqrt(this.dot(this));
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
    // can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(y, x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        double diff = other.angle() -  this.angle();
        if (diff > Math.PI){
            diff -= 2 * Math.PI;
        }else if (diff < -Math.PI){
            diff += 2 * Math.PI;
        }
        return diff;
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x += v.x * fac;
        this.y += v.y * fac;
        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        this.x *= fac;
        this.y *= fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double nx = (this.x * Math.cos(angle)) - (this.y * Math.sin(angle));
        double ny = (this.x * Math.sin(angle)) + (this.y * Math.cos(angle));
        x = nx;
        y = ny;
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {
        return this.x*v.x + this.y*v.y;
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return Math.sqrt(Math.pow(x-v.x, 2) + Math.pow(y-v.y, 2));
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        double nx = this.x / this.mag();
        double ny = this.y / this.mag();
        this.x = nx;
        this.y = ny;
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
    public Vector2D wrap(double w, double h) {
        if(this.x > w){
            this.x = x % w;
        }
        else if(this.x < 0){
            this.x = (x + w) % w;
        }
        if(this.y > h){
            this.y = y % h;
        }
        else if(this.y < 0){
            this.y =  (y + h) % h;
        }
        return this;
    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        return new Vector2D(Math.cos(angle) * mag, Math.sin(angle) * mag);
    }

}