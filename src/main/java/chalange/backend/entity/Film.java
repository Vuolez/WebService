package chalange.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "films")
public class Film extends BaseEntity {
    @Column(name = "year")
    private String year;

    @Column(name = "category")
    private String category;

    @Column(name = "nominee")
    private String nominee;

    @Column(name = "info")
    private String info;

    @Column(name = "won")
    private boolean isWon;

    public boolean getIsWon() {
        return isWon;
    }
}
