package l2s.gameserver.network.l2.s2c;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;

public class ExOlympiadSpelledInfoPacket extends L2GameServerPacket
{
	// chdd(dhd)
	private int char_obj_id = 0;
	private List<Effect> _effects;

	class Effect
	{
		int skillId;
		int level;
		int duration;

		public Effect(int skillId, int level, int duration)
		{
			this.skillId = skillId;
			this.level = level;
			this.duration = duration;
		}
	}

	public ExOlympiadSpelledInfoPacket()
	{
		_effects = new ArrayList<Effect>();
	}

	public void addEffect(int skillId, int level, int duration)
	{
		_effects.add(new Effect(skillId, level, duration));
	}

	public void addSpellRecivedPlayer(Player cha)
	{
		if(cha != null)
			char_obj_id = cha.getObjectId();
	}

	@Override
	protected final void writeImpl()
	{
		writeD(char_obj_id);
		writeD(_effects.size());
		for(Effect temp : _effects)
		{
			writeD(temp.skillId);
			writeH(temp.level);
			writeD(0x00); // UNK Ertheia
			writeH(temp.duration);
		}
	}
}