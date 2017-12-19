

import java.util.*;


public class DFA {
	private Set<String> states,acceptStates;
	private String startState;
	private Set<Character> alphabet;
	private Map<String,Map<Character,String>> transitions;


	public DFA(Set<Character> sigma) {
		alphabet=sigma;
		states=new HashSet<String>();
		acceptStates=new HashSet<String>();
		startState=null;
		transitions=new HashMap<String,Map<Character,String>>();

	}

	public DFA(char[] sigma) {
		this(new HashSet<Character>());
		for (char c: sigma) {
			alphabet.add((Character)(c));
		}
	}

	public DFA(String sigma) {
		this(sigma.toCharArray());
	}

	public void setStartState(String q0) {
		if (!isState(q0)) {
			throw new DFAException(
				String.format("State %s is not present",q0)
				);
		}
		startState=q0;
	}

	public void addState(String q, boolean accept) {
		if (q==null) {
			throw new DFAException(
				"Input string is null."
				);
		}
		if (!states.contains(q)) {
			states.add(q);
			if (accept) {
				acceptStates.add(q);
			}
			transitions.put(q,
				new HashMap<Character,String>()
				);
			for (Character ch : alphabet) {
				transitions.get(q).put(ch,q);
			}
		}
	}

	public boolean isState(String q) {
		return states.contains(q);
	}

	public boolean isAcceptState(String q) {
		return acceptStates.contains(q);
	}

	public void setAcceptState(String q) {
		if (isState(q)) {
			acceptStates.add(q);
		}
	}

	public void setRejectState(String q) {
		if (isAcceptState(q)) {
			acceptStates.remove(q);
		}
	}

	public boolean isAlphabetChar(char c) {
		return alphabet.contains((Character)(c));
	}

	public void setTransition(String from,char c,String to) {
		if (!isState(from) || !isState(to) || 
			!isAlphabetChar(c)) {
			throw new DFAException("Invalid transition");
		}
		transitions.get(from).put((Character)(c),to);
	}

	public int numStates() {
		return states.size();
	}

	public int numAcceptStates() {
		return acceptStates.size();
	}

	public Set<String> iterStates() {
		return new HashSet<String>(states);
	}

	public Set<String> iterAcceptStates() {
		return new HashSet<String>(acceptStates);
	}

	public String getTransition(String from, char c) {
		if (!isState(from) || 
			!isAlphabetChar(c)) {
			throw new DFAException("Invalid transition");
		}
		return transitions.get(from).get((Character)(c));
	}

	public int numAlphabetChar() {
		return alphabet.size();
	}

	public Set<Character> iterAlphabet() {
		return new HashSet<Character>(alphabet);
	}

	public boolean accept(String inputString) {
		return accept(inputString,false);
	}

	public boolean accept(String inputString,
		boolean allowInvalidChar) {

		String currState= getStartState();

		if (currState==null) {
			throw new DFAException(
				"start state is not yet set."
				);
		}

		for (char c: inputString.toCharArray()) {
			if (!isAlphabetChar(c)) {
				if (!allowInvalidChar) {
				throw new DFAException(
					"Invalid Character encountered."
					);
				}
				else {
					return false;
				}
			}
			currState=getTransition(currState,c);
		}
		return isAcceptState(currState);
	}

	public String getStartState() {
		return startState;
	}

	public String toString() {
		String desc="Decription of DFA:";
		desc+='\n';
		desc+="Alphabet: ";
		for (Character c: iterAlphabet()) {
			desc+=c+" ";
		}
		desc+='\n';
		desc+="States: ";
		for (String q: iterStates()) {
			desc+=String.format("\"%s\" ",q);
		}
		desc+='\n';
		desc+="Start State: "+String.format("\"%s\" ",
			startState);
		desc+='\n';
		desc+="Accept States: ";
		for (String q: iterAcceptStates()) {
			desc+=String.format("\"%s\" ",q);
		}
		desc+='\n';
		desc+="Transitions:\n";
		for (String q: iterStates()) {
			for (Character c: iterAlphabet()) {
				desc+=String.format(
					"\"%s\", %c ==> %s\n",
					q,c,getTransition(q,c)
					);
			}
		}
		return desc;
	}


	public static DFA readDFA(Scanner sc) {
		int nchars=sc.nextInt();
		char[]sigma=new char[nchars];
		for (int i=0;i<nchars;i++) {
			sigma[i]=sc.next().charAt(0);
		}
		DFA M=new DFA(sigma);

		int nstates=sc.nextInt();
		String [] stateArray=new String[nstates];
		for (int i=0;i<nstates;i++) {
			stateArray[i]=sc.next();
			M.addState(stateArray[i],false);
		}
		M.setStartState(stateArray[0]);
		int naccept=sc.nextInt();
		for (int i=0;i<naccept;i++) {
			M.setAcceptState(sc.next());
		}
		int numtrans=nchars*nstates;
		for (int i=0;i<numtrans;i++) {
			String from= sc.next();
			char c= sc.next().charAt(0);
			String to = sc.next();
			M.setTransition(from,c,to);
		}
		return M;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		DFA M = readDFA(sc);
		System.out.println();
		System.out.println(M);
		System.out.println("Enter input string? (y/n)");
		String prompt=sc.next();
		while (prompt.equals("y")) {
			System.out.println("Your input string:");
			String ips=sc.next();
			System.out.printf(
				"%s is %saccepted by the DFA.\n",ips,
				M.accept(ips,true)? "":"not " );
			System.out.println("Enter input string? (y/n)");
			prompt=sc.next();
		}
	}


}


class DFAException extends RuntimeException {
	DFAException() {}
	DFAException(String msg) {super(msg);}
}





 