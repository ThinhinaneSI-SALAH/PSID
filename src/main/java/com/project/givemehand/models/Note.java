package com.project.givemehand.models;

import javax.persistence.*;

@Entity
@Table ( name ="Note")
public class Note {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int note1 = 0;
    private int note2 = 0;
    private int note3 = 0;
    private int note4 = 0;
    private int note5 = 0;
    @OneToOne(mappedBy = "note")
    private Offre offre;

    public Note(Offre offre) {
        this.offre = offre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNote1() {
        return note1;
    }

    public void setNote1() {
        this.note1 ++;
    }

    public int getNote2() {
        return note2;
    }

    public void setNote2() {
        this.note2++;
    }

    public int getNote3() {
        return note3;
    }

    public void setNote3() {
        this.note3++;
    }

    public int getNote4() {
        return note4;
    }

    public void setNote4() {
        this.note4++;
    }

    public int getNote5() {
        return note5;
    }

    public void setNote5() {
        this.note5++;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}
