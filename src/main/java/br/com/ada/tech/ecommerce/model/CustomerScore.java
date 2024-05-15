package br.com.ada.tech.ecommerce.model;

public class CustomerScore {

    private Double score;
    private String origin;

    public static CustomerScore EMPTY = new CustomerScore(0.0, "Undefined");

    public CustomerScore(Double score, String origin) {
        this.score = score;
        this.origin = origin;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
