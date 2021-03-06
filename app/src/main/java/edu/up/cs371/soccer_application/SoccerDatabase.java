package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> players = new Hashtable<>();



    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {

        if( players.containsKey(firstName + " ## " + lastName )) {
         return false;
        }
        players.put(firstName + " ## " + lastName, new SoccerPlayer(firstName,lastName,uniformNumber,teamName));
        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String removeHash = firstName + " ## " + lastName;
        if(players.containsKey(removeHash)){
            players.remove(removeHash);
            return true;
        } else { return false; }


    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */

    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {


        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            return players.get(hashKey);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpGoals();

            return true;
        }

        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {

        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpAssists();

            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {


        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpShots();

            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {


        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpSaves();

            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {


        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpFouls();

            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {


        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpYellowCards();

            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {

        String hashKey = firstName + " ## " + lastName;

        if( players.containsKey( hashKey )){
            SoccerPlayer sp = players.get(hashKey);
            sp.bumpRedCards();

            return true;
        }


        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

        Hashtable<Integer, SoccerPlayer> team = new Hashtable<>();
        if( teamName == null ){
            return players.size();
        }

        int count = 0;
        Set<String> keys = players.keySet();

        for(String key : keys){
            SoccerPlayer sp = players.get(key);
            if( sp.getTeamName().equalsIgnoreCase(teamName)){
                count++;
            }
        }

        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        if( teamName == null ){
            return null;
        }

        Hashtable<Integer,SoccerPlayer> teams = new Hashtable<>();
        //Hashtable<Integer,String> teamNames = new Hashtable<>();

        Set<String> keys = players.keySet();

        int pos = 0;




        for( String key : keys ){

            SoccerPlayer sp = players.get(key);

            teams.put(pos, sp);
            pos++;

        }

        int pos2 = -1;

        for( int i = 0; i < pos; i++ ){

            if(teams.get(i).getTeamName().equalsIgnoreCase(teamName)){

                pos2++;

            }

            if( pos2 == idx ){
                return teams.get(i);
            }

        }

        return null;
    }




    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        return false;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
