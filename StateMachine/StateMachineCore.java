package StateMachine;

import java.util.HashMap;

import com.google.common.collect.HashMultimap;

import Core.MyHeroMain;
import MobManager.MyHeroMob;

public class StateMachineCore implements State{

	private HashMap<States,State> _State;
	
	private HashMultimap<States,HashMap<Triggers,States>> _Transition;
	
	private States _currentState;
	//private MyHeroMob Mob;
	
	
	public StateMachineCore(MyHeroMob mob)
	{
		//Mob = mob;
		_State = new HashMap<>();
		_Transition = HashMultimap.create(); 
		_currentState = States.Idle;
	}
	public void addState(State state,States states)
	{
		_State.put(states, state);
	}
	
	
	
	public void Add(States state,Triggers trigger,States targetState)
	{
		HashMap<Triggers,States> tmp = new HashMap<>();
		tmp.put(trigger, targetState);
		_Transition.put(state, tmp);
	}
	
	public void Fire(Triggers triger)
	{
		if(_Transition.containsKey(_currentState))
		{
			for(HashMap<Triggers,States> v : _Transition.get(_currentState))
			{
				if(v.containsKey(triger)) { _currentState = v.get(triger);break;}
			}
		}
		
		
	}
	public void Execute()
	{
		
		_State.get(_currentState).Execute();
	}
	
	public StateMachineConfigure getConfigure()
	{
		return new StateMachineConfigure(this);
	}
	
	public void Log()
	{
		MyHeroMain.Main.getLogger().info(_State.toString());
		MyHeroMain.Main.getLogger().info(_Transition.toString());
	}
}
