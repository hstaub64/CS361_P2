package fa.nfa;
import java.util.HashSet;
import java.util.HashMap;

import fa.State;

/**
 * NFAState is an extension of State.java which holds states for an NFA.
 * Each state has a name and a hash table of associated transitions using
 * <K, V> -> symbol, transitions
 * @author Dean Cunningham, Haylee Staub
 */
public class NFAState extends State{

    private HashMap<Character, HashSet<NFAState>> transitions;

    /**
     * Constructor for NFAState. Inherited from State.java
     * @param name
     */
    public NFAState(String name){
        super(name);
        this.transitions = new HashMap<>();
        transitions.put('e', new HashSet<>());  //Initialize epsilon transitions
    }

    public void AddTransition(char symbol, NFAState toState){
        transitions.computeIfAbsent(symbol, k -> new HashSet<NFAState>()).add(toState);
    }

    public HashSet<NFAState> GetTransitions(char symbol){
        return transitions.get(symbol);
    }

}
