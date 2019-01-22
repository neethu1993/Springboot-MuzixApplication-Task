/**
 * Muzix Application domain class and used lombok annotations to reduce the number of codes
 */
package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Muzix {

    //Variables
    @Id
    private int trackId;
    private String trackName;
    private String comment;
}
