package com.xworkz.fields.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "place_info")
@NamedQueries({
        @NamedQuery(name = "findByPlaceName", query = "from PlaceEntity where name = :nm"),
        @NamedQuery(name = "findByCity", query = "from PlaceEntity where city = :ct"),
        @NamedQuery(name = "findByFamousFor", query = "from PlaceEntity where famousFor = :ff")
})
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "place_name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "famous_for")
    private String famousFor;

    @Column(name = "pincode")
    private Integer pincode;

}
