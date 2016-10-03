package br.com.mertins.ufpel.am.perceptron;

/**
 *
 * @author mertins
 */
class Sinaps {

    private final double in;
    private double weight;

    public Sinaps(double in, double weight) {
        this.in = in;
        this.weight = weight;
    }

    public double getIn() {
        return in;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}