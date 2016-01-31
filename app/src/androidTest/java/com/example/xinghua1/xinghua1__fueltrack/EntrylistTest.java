package com.example.xinghua1.xinghua1__fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xinghua1 on 1/31/16.
 */
public class EntrylistTest extends ActivityInstrumentationTestCase2 {

    public EntrylistTest() {
        super(AddNewEntryActivity.class);
    }
    public void testAddEntry(){
        Date datetext = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datetext = df.parse("1990-01-20");
        } catch (ParseException e) {
            e.printStackTrace();

            Initialization entry = new Initialization(datetext, "superstore", "100022", "91", "100", "10");
            Entrylist entrylist = new Entrylist();

            entrylist.AddEntrylist(entry);


            assertTrue(entrylist.hasenrty(entry));
        }
    }
    public void testGet(){
        Date datetext = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datetext = df.parse("1990-01-20");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Initialization entry0 = new Initialization(datetext,"superstore","100022","91","100","10");
        Entrylist entrylist = new Entrylist();
        entrylist.AddEntrylist(entry0);
        Initialization entry1 = entrylist.getEntry(0);
        assertEquals(entry1.getDate(),entry0.getDate());
        assertEquals(entry1.getStation(), entry0.getStation());
        assertEquals(entry1.getOdometer(),entry0.getOdometer());
        assertEquals(entry1.getFuelAmount(),entry0.getFuelAmount());
        assertEquals(entry1.getFuelGrade(),entry0.getFuelGrade());
        assertEquals(entry1.getFuelUnitCost(),entry0.getFuelUnitCost());
        assertEquals(entry1.getFuelCost(),entry0.getFuelCost());
    }
    public void testDeleteEntry(){
        Date datetext = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datetext = df.parse("1990-01-20");
        } catch (ParseException e) {
            e.printStackTrace();
        Initialization entry = new Initialization(datetext,"superstore","100022","91","100","10");
        Entrylist entrylist = new Entrylist();

        entrylist.AddEntrylist(entry);
        entrylist.RemoveEntry(entry);
        assertFalse(entrylist.hasenrty(entry));
    }
    }
    public void testCount(){
        Date datetext1 = null;
        Date datetext2 = null;
        Date datetext3 = null;
        Date datetext4 = null;
        Date datetext5 = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datetext1 = df.parse("1990-01-21");
            datetext2 = df.parse("1991-01-22");
            datetext3 = df.parse("1992-01-23");
            datetext4 = df.parse("1993-01-24");
            datetext5 = df.parse("1994-01-25");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Entrylist entrylist = new Entrylist();
            Initialization entry1 = new Initialization(datetext1,"superstore","110000","91","100","10");
            Initialization entry2 = new Initialization(datetext2,"superstore","123333","87","100","10");
            Initialization entry3 = new Initialization(datetext3,"superstore","145444","94","100","10");
            Initialization entry4 = new Initialization(datetext4,"superstore","166666","diesel","100","10");
            Initialization entry5 = new Initialization(datetext5,"superstore","544444","91","100","10");
        entrylist.AddEntrylist(entry1);
        entrylist.AddEntrylist(entry2);
        entrylist.AddEntrylist(entry3);
        entrylist.AddEntrylist(entry4);
        entrylist.AddEntrylist(entry5);
        assertTrue(entrylist.getCount() == 5);
    }
}
