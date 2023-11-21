package telran.view;

public class Menu implements Item {
	private static final String SYMBOL = "*";
	private static final int N_SYMBOLS = 30;
	Item[] items;
	String name;
	
	public Menu(Item[] items, String name) {
		super();
		this.items = items;
		this.name = name;
	}
	
	@Override
	public String displayName() {
		
		return name;
	}
	@Override
	public void perform(InputOutput io) {
		Item item = null;
		do {
			displayTitle(io);
			displayItems(io);
			int nItem = io.readInt("Select item number", "Wrong item number", 1, items.length);
			item = items[nItem - 1];
			try {
				item.perform(io);
			}catch (Exception e) {
				io.writeLine(e.getMessage());
			}
		} while (!item.isExit());
	}
	
	private void displayItems(InputOutput io) {
		for (int i = 0; i < items.length; i++) {
			io.writeLine(String.format("%d.%s", i + 1, items[i].displayName()));
		}
		
	}

	public void displayTitle(InputOutput io) {
	    io.writeLine(SYMBOL.repeat(N_SYMBOLS));
	    io.writeLine(String.format("%s%s%s", SYMBOL, " ".repeat(N_SYMBOLS / 4), name));
	    io.writeLine(SYMBOL.repeat(N_SYMBOLS));
	  }
	@Override
	public boolean isExit() {
		// TODO Auto-generated method stub
		return false;
	}

}
