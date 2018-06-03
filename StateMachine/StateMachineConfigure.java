package StateMachine;

public class StateMachineConfigure {

	private StateMachineCore Machine;
	private States state;
	public StateMachineConfigure(StateMachineCore core)
	{
		Machine = core;
	}
	
	public StateMachineConfigure Configure(States state)
	{
		this.state = state;
		return this;
	}
	
	public StateMachineConfigure Add(Triggers trigger,States state)
	{
		Machine.Add(this.state, trigger,state);
		return this;
	}
}
