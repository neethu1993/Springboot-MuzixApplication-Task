package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixNotFoundException;
import com.stackroute.repository.MuzixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MuzixServiceTest {
    private Muzix muzix;

    //Create a mock for MuzixRepository
    @Mock
    private MuzixRepository muzixRepository;

    //Inject the mocks as dependencies into MuzixServiceImpl
    @InjectMocks
    private MuzixServiceImpl muzixService;
    List<Muzix> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        muzix = new Muzix();
        muzix.setTrackName("John");
        muzix.setTrackId(101);
        muzix.setComment("Jenny");
        list = new ArrayList<>();
        list.add(muzix);
    }

    @Test
    public void saveMuzixTestSuccess() throws MuzixAlreadyExistsException {

        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix = muzixService.saveMuzix(muzix);
        Assert.assertEquals(muzix, savedMuzix);

        //verify here verifies that muzixRepository save method is only called once
        verify(muzixRepository, times(1)).save(muzix);

    }

    @Test(expected = MuzixAlreadyExistsException.class)
    public void saveMuzixTestFailure() throws MuzixAlreadyExistsException {
        when(muzixRepository.save((Muzix) any())).thenReturn(null);
        Muzix savedMuzix = muzixService.saveMuzix(muzix);
        System.out.println(savedMuzix);
        Assert.assertEquals(null, savedMuzix);
    }

    @Test
    public void updateMuzixTestSuccess() throws MuzixNotFoundException {
        when(muzixRepository.findById(anyInt())).thenReturn(Optional.of(muzix));
        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        muzix.setComment("Johns");
        Muzix savedMuzix = muzixService.updateMuzix(muzix.getTrackId(),muzix.getComment());
        Assert.assertEquals(muzix,savedMuzix);

        //verify here verifies that muzixRepository save method is only called once
        verify(muzixRepository, times(1)).save(muzix);

    }

    @Test(expected = MuzixNotFoundException.class)
    public void updateMuzixTestFailure() throws MuzixNotFoundException {
        when(muzixRepository.findById(11)).thenReturn(Optional.of(muzix));
        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix = muzixService.updateMuzix(muzix.getTrackId(),"gfcghgv");
        System.out.println(savedMuzix);
        Assert.assertEquals(null, savedMuzix);
    }

    @Test
    public void getAllMuzixTestSuccess() throws MuzixNotFoundException{
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> muzixs = muzixService.getAllMuzixs();
        Assert.assertEquals(list,muzixs);
    }

    @Test(expected = MuzixNotFoundException.class)
    public void getAllMuzixTestFaiure() throws MuzixNotFoundException{
        List<Muzix> muzixes=muzixService.getAllMuzixs();
        Assert.assertNull(muzixes);
    }

    @Test
    public void trackByIdTestSuccess()throws MuzixNotFoundException{
        when(muzixRepository.findById((anyInt()))).thenReturn(Optional.of(muzix));
        Muzix actualOutput = muzixService.trackByTrackId(101);
        Assert.assertEquals(muzix,actualOutput);

        //verify here verifies that muzixRepository findbyId method is only called 2 times
        verify(muzixRepository, times(2)).findById(101);
    }

    @Test(expected = MuzixNotFoundException.class)
    public void trackByIdTestFailure()throws MuzixNotFoundException{
        when(muzixRepository.findById((101))).thenReturn(Optional.of(muzix));
        Muzix actualOutput = muzixService.trackByTrackId(112);
        Assert.assertEquals(muzix,actualOutput);
    }

    @Test
    public void trackByTrackNameTestSuccess()throws MuzixNotFoundException{
        when(muzixRepository.findByTrackName((anyString()))).thenReturn(list);
        List<Muzix> actualOutput = muzixService.trackByTrackName("John");
        Assert.assertEquals(list,actualOutput);

        //verify here verifies that muzixRepository findbytrackname method is only called twice
        verify(muzixRepository, times(2)).findByTrackName("John");
    }

    @Test(expected = MuzixNotFoundException.class)
    public void trackByTrackNameTestFailure()throws MuzixNotFoundException{
        when(muzixRepository.findByTrackName(("John"))).thenReturn(list);
        List<Muzix> actualOutput = muzixService.trackByTrackName("Johns");
        Assert.assertEquals(muzix,actualOutput);
    }

    @Test
    public void removeMuzixTestSuccess()throws MuzixNotFoundException{
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> actualoutput= muzixService.removeMuzix(muzix.getTrackId());
        Assert.assertEquals(list,actualoutput);

        //verify here verifies that muzixRepository deletebyId method is only called once
        verify(muzixRepository, times(1)).deleteById(eq(101));
    }

    @Test(expected = MuzixNotFoundException.class)
    public void removeMuzixTestFaiure()throws MuzixNotFoundException{
        when(muzixRepository.existsById(1)).thenReturn(true);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> actualoutput= muzixService.removeMuzix(muzix.getTrackId());
        Assert.assertEquals(list,actualoutput);
    }
}