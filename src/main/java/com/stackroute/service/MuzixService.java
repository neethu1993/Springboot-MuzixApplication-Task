/**
 * Interface that contains the services peerformed by muzix application
 */
package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixNotFoundException;

import java.util.List;

public interface MuzixService {
    public Muzix saveMuzix(Muzix muzix)throws MuzixAlreadyExistsException;
    public List<Muzix> getAllMuzixs()throws MuzixNotFoundException;
    public Muzix updateMuzix(int trackId,String comment)throws MuzixNotFoundException;
    public Muzix removeMuzix(int trackId) throws MuzixNotFoundException;
    public Muzix trackByTrackId(int trackId) throws MuzixNotFoundException;
    public List<Muzix> trackByTrackName(String trackName)throws MuzixNotFoundException;
}
