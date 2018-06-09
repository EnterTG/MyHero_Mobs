package StateMachine;

import Core.MyHeroMain;

public class IdleState implements State{

	
	private int Amount = 0;
	private StateMachineCore Machine;
	public IdleState(StateMachineCore machinecore)
	{
		Machine = machinecore;
	}
	@Override
	public void Execute() {
		//MyHeroMain.Main.getLogger().info("Amount: " + Amount);
		if(Amount >= 20) 
		{
			Amount = 0;
			Machine.Fire(Triggers.EndWait);
		}
		else
			Amount++;
		
	}

}
