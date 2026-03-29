package fa.nfa;

import java.util.Set;
import java.util.LinkedHashSet; //Linked version is used for consistency of toString methods
import java.util.HashSet;
import java.util.LinkedHashMap;

import fa.State;

/**
 * NFA.java implements NFAInterface and instantiates an object with the properties
 * of a Non-Finite Automata.
 * @author Dean Cunningham, Haylee Staub
 */
public class NFA implements NFAInterface{

    //Define elements of the 5-tuple
    private LinkedHashMap<String, NFAState> states;
    private LinkedHashSet<Character> sigma;
    private LinkedHashSet<String> finalStates;    //This just stores the names since we don't need duplicate NFAStates
    private NFAState startState;
    //Transitions are held within the NFAState objects

    /**
     * Constructor for NFA. Takes no parameters and returns an empty
     * NFA with a blank 5-tuple.
     */
    public NFA(){
        this.states = new LinkedHashMap<>();
        this.sigma = new LinkedHashSet<>();
        this.finalStates = new LinkedHashSet<>();
        this.startState = null;
        this.sigma.add('e');    //epsilon is valid regardless of sigma
    }

    @Override
    public boolean addState(String name) {
        //Exit if the state already exists
        if(states.get(name) != null){
            return false;
        }
        states.put(name, new NFAState(name));
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        //Exit if the final state already exists or the state doesn't exist
        if(!states.containsKey(name) || finalStates.contains(name)){
            return false;
        }
        finalStates.add(name);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        //Exit if the new start state does not exist
        if(!states.containsKey(name)){
            return false;
        }
        startState = states.get(name);
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        //Exit if the character is already part of sigma
        if(sigma.contains(symbol)){ 
            return; 
        }
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return this.sigma;
    }

    @Override
    // Temp change to NFAState return type to run tests, change later
    public NFAState getState(String name) {
        return states.get(name);
    }

    @Override
    public boolean isFinal(String name) {
        return finalStates.contains(name);
    }

    @Override
    public boolean isStart(String name) {
        //Returns true only if a start state is defined and matches
        return(startState != null && startState.getName().equals(name));
    }

    //Unused
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> returnSet = new HashSet<>();
        returnSet.add(s);

        // failing test 3_4
        for (NFAState state : s.GetTransitions('e')) {
            if (state != null) {
                returnSet.add(state);
            }
        }

        return returnSet;
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        //First, check that the transition is valid
        if (!(sigma.contains(onSymb) && states.containsKey(fromState))) {
            return false;
        }
        for (String string : toStates) {
            //Ensure that each toState is valid
            if (!states.containsKey(string)) {
                return false;
            }
            NFAState state = states.get(string);
            states.get(fromState).AddTransition(onSymb, state);
        }
        return true;
    }

    @Override
    // check for three conditions:
    // 1) more than one transition on the same symbol
    // 2) no transition for any symbol in sigma
    // 3) any e transitions
    public boolean isDFA() {
        // much better way to do this, will fix later
        for (String name : states.keySet()) {
            for (char symbol : sigma) {
                if (states.get(name).GetTransitions(symbol) == null) {
                    return false;
                } else if (states.get(name).GetTransitions(symbol).size() > 1) {
                    return false;
                } else if (symbol == 'e' && states.get(name).GetTransitions(symbol) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    
}
