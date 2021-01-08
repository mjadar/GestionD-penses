package sample;


import java.sql.Date;

public class Saving {
    int pos;
    Date date_jour;
    int dejeuner;
    int midi;
    int diner;
    int courses;
    int taxi;
    int factures;
    int other;
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Saving(int pos, Date date_jour, int dejeuner, int midi, int diner, int courses, int taxi, int factures, int other, int total) {
        this.pos = pos;
        this.date_jour = date_jour;
        this.dejeuner = dejeuner;
        this.midi = midi;
        this.diner = diner;
        this.courses = courses;
        this.taxi = taxi;
        this.factures = factures;
        this.other = other;
        this.total = total;
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Date getDate_jour() {
        return date_jour;
    }

    public void setDate_jour(Date date_jour) {
        this.date_jour = date_jour;
    }

    public int getDejeuner() {
        return dejeuner;
    }

    public void setDejeuner(int dejeuner) {
        this.dejeuner = dejeuner;
    }

    public int getMidi() {
        return midi;
    }

    public void setMidi(int midi) {
        this.midi = midi;
    }

    public int getDiner() {
        return diner;
    }

    public void setDiner(int diner) {
        this.diner = diner;
    }

    public int getCourses() {
        return courses;
    }

    public void setCourses(int courses) {
        this.courses = courses;
    }

    public int getTaxi() {
        return taxi;
    }

    public void setTaxi(int taxi) {
        this.taxi = taxi;
    }

    public int getFactures() {
        return factures;
    }

    public void setFactures(int factures) {
        this.factures = factures;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }


}
