package com.stackroute.repository;

import com.stackroute.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
@RunWith(SpringRunner.class)

// Used when a test focuses only on MongoDB components.
@DataMongoTest
public class MuzixRepositoryTest {
    @Autowired
    private MuzixRepository muzixRepository;
    private Muzix muzix;

    @Before
    public void setUp()
    {
        muzix = new Muzix();
        muzix.setTrackName("John");
        muzix.setTrackId(101);
        muzix.setComment("Jenny");

    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        muzixRepository.save(muzix);
        Muzix fetchUser = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(101,fetchUser.getTrackId());

    }

    @Test
    public void testSaveUserFailure(){
        Muzix testMuzix = new Muzix(201,"john","Harry123");
        muzixRepository.save(muzix);
        Muzix fetchMuzix = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(testMuzix,muzix);
    }

    @Test
    public void testGetAllMuzixs(){
        Muzix muzix1 = new Muzix(102,"Johnny","Jenny");
        Muzix muzix2 = new Muzix(103,"Harry","harry123");
        muzixRepository.save(muzix1);
        muzixRepository.save(muzix2);

        List<Muzix> list = muzixRepository.findAll();
        Assert.assertEquals("Johnny",list.get(0).getTrackName());
    }

    @Test
    public void testDeleteByIdSuccess(){
        muzixRepository.save(muzix);
        muzixRepository.deleteById(101);
        Assert.assertEquals(false,muzixRepository.existsById(101));
    }

    @Test
    public void testDeleteByIdFailure(){
        muzixRepository.save(muzix);
        Muzix muzix1 = new Muzix(102,"Johnny","Jenny");
        muzixRepository.save(muzix1);
        muzixRepository.deleteById(101);
        Assert.assertEquals(true,muzixRepository.existsById(102));
    }

    @Test
    public void testFindByTrackNameSuccess(){
        List<Muzix> expectedOutput = new ArrayList<>();
        muzixRepository.save(muzix);
        expectedOutput.add(muzix);
        List<Muzix> output=muzixRepository.findByTrackName("John");
        System.out.println(output);
        System.out.println(expectedOutput);
        Assert.assertEquals(expectedOutput,output);
    }

    @Test
    public void testFindByTrackNameFaiure(){
      List<Muzix> expectedOutput = new ArrayList<>();
       expectedOutput.add(muzix);
        List<Muzix> output=muzixRepository.findByTrackName("John");
        Assert.assertNotEquals(expectedOutput,output);
    }

    @Test
    public void testFindByIdSuccess(){
        muzixRepository.save(muzix);
        Muzix output=muzixRepository.findById(101).get();
        System.out.println(output);
        Assert.assertEquals(muzix,output);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByTrackIdFaiure(){
        Muzix output=muzixRepository.findById(101).get();
        System.out.println(output);
        Assert.assertEquals(null,output);
    }
}
