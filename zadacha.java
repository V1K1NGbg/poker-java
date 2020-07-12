import java.util.*;

class Card {
	private static Class deck[];
	private static int currentCard;
	private final int number = 52;
	private Random randomNumbers;

	public Card() {

		String faces[] = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
				"King", "Ace" };

		String suit[] = { "Hearts", "Diamonds", "Clubs", "Spades" };

		deck = new Class[number];
		currentCard = 0;
		randomNumbers = new Random();

		for (int i = 0; i < deck.length; i++) {
			deck[i] = new Class(faces[i % 13], suit[i / 13]);
		}
	}

	public void shuffle() {
		currentCard = 0;
		for (int i = 0; i < deck.length; i++) {
			int j = randomNumbers.nextInt(number);
			Class temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
	}

	public static Class dealCard() {
		if (currentCard < deck.length) {
			return deck[currentCard++];
		} else
			return null;
	}
}

public class zadacha {
	public static int playermoney = 100, compmoney = 100, handbr = 1;
	public static String[] playercards = new String[3], compcards = new String[3], middlecards = new String[6];
	public static boolean playerfirst = true, skip = false, playerwinner, foldwin = false;
	public static int tablemoney, prank, crank, pvalue, cvalue, rank, value;
	public static int[] cardsintsort = new int[8], pcardsintsort = new int[8], ccardsintsort = new int[8];

	public static Scanner in = new Scanner(System.in);

	public static void begining() {
		playercards[1] = Card.dealCard().toString();
		playercards[2] = Card.dealCard().toString();
		compcards[1] = Card.dealCard().toString();
		compcards[2] = Card.dealCard().toString();
		middlecards[1] = Card.dealCard().toString();
		middlecards[2] = Card.dealCard().toString();
		middlecards[3] = Card.dealCard().toString();
		tablemoney = 10;
		playermoney = playermoney - 5;
		compmoney = compmoney - 5;
	}

	public static void display(int i) {
		System.out.println("Your Cards: " + playercards[1] + " , " + playercards[2] + "\n" + "Your Money: "
				+ playermoney + " Computer's Money: " + compmoney + "\n");
		for (int j = 1; j <= i; j++) {
			System.out.println(middlecards[j]);
		}
		System.out.println();
	}

	public static void turn() {
		if (skip == false) {

			if (playerfirst) {
				// player
				System.out.println("What action do you whant to perform? ");
				System.out.println("1. fold ");
				System.out.println("2. raise ");
				System.out.println("3. check ");
				System.out.println("4. all in");
				int ans = in.nextInt();
				switch (ans) {
					case 1:
						System.out.println();
						System.out.println("The computer will win automatically.");
						System.out.println();
						skip = true;
						playerwinner = false;
						foldwin = true;
						break;

					case 2:
						int raise;
						do {
							System.out.println("How much do you what to raise? ");
							raise = in.nextInt();
						} while (playermoney - raise < 0);

						tablemoney = tablemoney + raise;
						playermoney = playermoney - raise;

						// computer
						if (compmoney > 10) {
							switch ((int) (Math.random() * 10 + 1)) {
								case 1:
									System.out.println("The computer folded.");
									skip = true;
									playerwinner = true;
									foldwin = true;
									break;
								case 2:
								case 3:
								case 4:
									while (true) {
										System.out.println("The computer raised.");
										raise = raise + 10;
										compmoney = compmoney - raise;
										tablemoney = tablemoney + raise;

										// player
										System.out.println("What action do you whant to perform? ");
										System.out.println("1. fold ");
										System.out.println("2. all in");
										if (playermoney > 10) {
											System.out.println("3. raise ");
											System.out.println("4. call ");
										}
										ans = in.nextInt();

										if (ans == 3) { // if the person wants to raise(and what the computer will do)
											do {
												System.out.println("How much do you what to raise? ");
												raise = in.nextInt();
											} while (playermoney - raise < 0);
											raise = raise + 10;
											tablemoney = tablemoney + raise;
											playermoney = playermoney - raise;
											// computer
											ans = (int) (Math.random() * 10 + 1);
											if (ans >= 1 && ans <= 7 && compmoney > raise - 10) {
												System.out.println("The computer called.");
												compmoney = compmoney - (raise - 10);
												tablemoney = tablemoney + (raise - 10);
												break;
											} else if (ans == 8) {
												System.out.println("The computer folded.");
												skip = true;
												playerwinner = true;
												foldwin = true;
												break;
											}
											// player
										} else {
											if (ans == 1) { // if the person wants to fold
												skip = true;
												playerwinner = false;
												foldwin = true;
											}
											if (ans == 4) { // if the person wants to call
												tablemoney = tablemoney + 10;
												playermoney = playermoney - 10;
											}

											if (ans == 2) { // if the person want to all in (and what the computer will
															// do)
												tablemoney = tablemoney + playermoney;
												playermoney = 0;
												// computer
												ans = ((int) (Math.random() * 3 + 1));
												if (ans == 1) {
													System.out.println("The computer folded.");
													skip = true;
													playerwinner = true;
													foldwin = true;
												}
												if (ans >= 2 && ans <= 3) {
													System.out.println("The computer all ined.");
													tablemoney = tablemoney + compmoney;
													compmoney = 0;
													skip = true;
												}
											}
											break;
										}
									}
									break;
								case 5:
								case 6:
								case 7:
								case 8:
								case 9:
								case 10:
									System.out.println("The computer called.");
									compmoney = compmoney - raise;
									tablemoney = tablemoney + raise;
									break;
							}
						} else {
							System.out.println("The computer folded.");
							skip = true;
							playerwinner = true;
							foldwin = true;
							break;
						}
						break;

					case 3: // player (nothing)
						// computer
						if (compmoney > 10) {
							switch ((int) (Math.random() * 10 + 1)) {
								case 1:
									System.out.println("The computer folded.");
									skip = true;
									playerwinner = true;
									foldwin = true;
									break;
								case 2:
								case 3:
								case 4:
									while (true) {
										System.out.println("The computer raised.");
										raise = 10;
										compmoney = compmoney - raise;
										tablemoney = tablemoney + raise;

										// player
										System.out.println("What action do you whant to perform? ");
										System.out.println("1. fold ");
										System.out.println("2. all in");
										if (playermoney > 10) {
											System.out.println("3. raise ");
											System.out.println("4. call ");
										}
										ans = in.nextInt();

										if (ans == 3) { // if the person wants to raise(and what the computer will do)
											do {
												System.out.println("How much do you what to raise? ");
												raise = in.nextInt();
											} while (playermoney - raise < 0);
											raise = raise + 10;
											tablemoney = tablemoney + raise;
											playermoney = playermoney - raise;
											// computer
											ans = (int) (Math.random() * 10 + 1);
											if (ans >= 1 && ans <= 7 && compmoney > raise - 10) {
												System.out.println("The computer called.");
												compmoney = compmoney - (raise - 10);
												tablemoney = tablemoney + (raise - 10);
												break;
											} else if (ans == 8) {
												System.out.println("The computer folded.");
												skip = true;
												playerwinner = true;
												foldwin = true;
												break;
											}
											// player
										} else {
											if (ans == 1) { // if the person wants to fold
												skip = true;
												playerwinner = false;
												foldwin = true;
											}
											if (ans == 4) { // if the person wants to call
												tablemoney = tablemoney + 10;
												playermoney = playermoney - 10;
											}

											if (ans == 2) { // if the person want to all in (and what the computer will
															// do)
												tablemoney = tablemoney + playermoney;
												playermoney = 0;
												// computer
												ans = ((int) (Math.random() * 3 + 1));
												if (ans == 1) {
													System.out.println("The computer folded.");
													skip = true;
													playerwinner = true;
													foldwin = true;
												}
												if (ans >= 2 && ans <= 3) {
													System.out.println("The computer all ined.");
													tablemoney = tablemoney + compmoney;
													compmoney = 0;
												}
											}
											break;
										}
									}
									break;
								case 5:
								case 6:
								case 7:
								case 8:
								case 9:
								case 10:
									System.out.println("The computer checked.");
									break;
							}
						} else {
							System.out.println("The computer folded.");
							skip = true;
							playerwinner = true;
							foldwin = true;
							break;
						}

						break;

					case 4:
						tablemoney = tablemoney + playermoney;
						playermoney = 0;
						// computer
						ans = ((int) (Math.random() * 3 + 1));
						if (ans == 1) {
							System.out.println("The computer folded.");
							skip = true;
							playerwinner = true;
							foldwin = true;
						}
						if (ans >= 2 && ans <= 3) {
							System.out.println("The computer all ined.");
							tablemoney = tablemoney + compmoney;
							compmoney = 0;
							skip = true;
						}

						break;
				}

				playerfirst = false;
			} else {
				// computer
				int ans = ((int) (Math.random() * 10 + 1));
				switch (ans) { // 1- fold; 2,3,4- raise; 5,6,7,8,9,10- check
					case 1:
						System.out.println("The computer folded.");
						System.out.println();
						System.out.println("The player will win automatically.");
						System.out.println();
						skip = true;
						playerwinner = true;
						foldwin = true;
						break;
					case 2:
					case 3:
					case 4:
						System.out.println("The computer raised.");
						int raise = 10;
						tablemoney = tablemoney + raise;
						compmoney = compmoney - raise;
						// player
						System.out.println("What action do you whant to perform? ");
						System.out.println("1. fold ");
						System.out.println("2. all in ");
						if (playermoney > 10) {
							System.out.println("3. call ");
							System.out.println("4. raise ");
						}
						ans = in.nextInt();
						switch (ans) {
							case 1:
								skip = true;
								playerwinner = false;
								foldwin = true;
								break;
							case 4:
								while (true) {
									do {
										System.out.println("How much do you what to raise? ");
										raise = in.nextInt();
									} while (playermoney - raise < 0);
									raise = raise + 10;
									playermoney = playermoney - raise;
									tablemoney = tablemoney + raise;

									// computer
									ans = ((int) (Math.random() * 10 + 1));

									if (ans >= 1 && ans <= 2) { // if the computer raise(and what the player will do)
										System.out.println("The computer raised.");
										tablemoney = tablemoney + raise;
										compmoney = compmoney - raise;
										// player
										System.out.println("What action do you whant to perform? ");
										System.out.println("1. fold ");
										System.out.println("2. all in ");
										if (playermoney > 10) {
											System.out.println("3. call ");
											System.out.println("4. raise ");
										}
										ans = in.nextInt();
										if (ans == 3) {
											playermoney = playermoney - (raise - 10);
											tablemoney = tablemoney + (raise - 10);
											break;
										} else if (ans == 1) {
											skip = true;
											playerwinner = false;
											foldwin = true;
											break;
										} else if (ans == 2) {
											tablemoney = tablemoney + playermoney;
											playermoney = 0;
											// computer
											ans = ((int) (Math.random() * 3 + 1));
											if (ans == 1) {
												System.out.println("The computer folded.");
												skip = true;
												playerwinner = true;
												foldwin = true;
											}
											if (ans >= 2 && ans <= 3) {
												System.out.println("The computer all ined.");
												tablemoney = tablemoney + compmoney;
												compmoney = 0;
											}
											break;
										}
										// computer
									} else {
										if (ans == 3) { // if the computer wants to fold
											System.out.println("The computer folded");
											skip = true;
											playerwinner = true;
											foldwin = true;
										}
										if (ans >= 4 && ans <= 10) { // if the computer wants to call
											System.out.println("The computer called");
											tablemoney = tablemoney + (raise - 10);
											compmoney = compmoney - (raise - 10);
										}
										break;
									}
								}
								break;
							case 3:
								playermoney = playermoney - 10;
								tablemoney = tablemoney + 10;
								break;
							case 2:
								tablemoney = tablemoney + playermoney;
								playermoney = 0;
								// computer
								ans = ((int) (Math.random() * 3 + 1));
								if (ans == 1) {
									System.out.println("The computer folded.");
									skip = true;
									playerwinner = true;
									foldwin = true;
								}
								if (ans >= 2 && ans <= 3) {
									System.out.println("The computer all ined.");
									tablemoney = tablemoney + compmoney;
									compmoney = 0;
									skip = true;
								}
								break;
						}
						break;

					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10: // computer (nothing)
						System.out.println("The computer checked.");
						// player
						System.out.println("What action do you whant to perform? ");
						System.out.println("1. fold ");
						System.out.println("2. all in ");
						if (playermoney > 10) {
							System.out.println("3. check ");
							System.out.println("4. raise ");
						}
						ans = in.nextInt();
						switch (ans) {
							case 1:
								skip = true;
								playerwinner = false;
								foldwin = true;
								break;
							case 4:
								while (true) {
									do {
										System.out.println("How much do you what to raise? ");

										raise = in.nextInt();
									} while (playermoney - raise < 0);
									raise = raise + 10;
									playermoney = playermoney - raise;
									tablemoney = tablemoney + raise;

									// computer
									ans = ((int) (Math.random() * 10 + 1));

									if (ans >= 1 && ans <= 2) { // if the computer raise(and what the player will do)
										System.out.println("The computer raised.");
										raise = raise + 10;
										tablemoney = tablemoney + raise;
										compmoney = compmoney - raise;
										// player
										System.out.println("What action do you whant to perform? ");
										System.out.println("1. fold ");
										System.out.println("2. all in ");
										if (playermoney > 10) {
											System.out.println("3. call ");
											System.out.println("4. raise ");
										}
										ans = in.nextInt();
										if (ans == 3) {
											playermoney = playermoney - (raise - 10);
											tablemoney = tablemoney + (raise - 10);
											break;
										} else if (ans == 1) {
											skip = true;
											playerwinner = false;
											foldwin = true;
											break;
										} else if (ans == 2) {
											tablemoney = tablemoney + playermoney;
											playermoney = 0;
											// computer
											ans = ((int) (Math.random() * 3 + 1));
											if (ans == 1) {
												System.out.println("The computer folded.");
												skip = true;
												playerwinner = true;
												foldwin = true;
											}
											if (ans >= 2 && ans <= 3) {
												System.out.println("The computer all ined.");
												tablemoney = tablemoney + compmoney;
												compmoney = 0;
												skip = true;
											}
											break;
										}
										// computer
									} else {
										if (ans == 3) { // if the computer wants to fold
											System.out.println("The computer folded");
											skip = true;
											playerwinner = true;
											foldwin = true;
										}
										if (ans >= 4 && ans <= 10) { // if the computer wants to call
											System.out.println("The computer called");
											tablemoney = tablemoney + raise;
											compmoney = compmoney - raise;
										}
										break;
									}
								}
								break;
							case 2:
								tablemoney = tablemoney + playermoney;
								playermoney = 0;
								// computer
								ans = ((int) (Math.random() * 3 + 1));
								if (ans == 1) {
									System.out.println("The computer folded.");
									skip = true;
									playerwinner = true;
									foldwin = true;
								}
								if (ans >= 2 && ans <= 3) {
									System.out.println("The computer all ined.");
									tablemoney = tablemoney + compmoney;
									compmoney = 0;
								}
								break;
						}
						break;
				}
				playerfirst = true;
			}
		}
	}

	public static void oneMoreCard(int i) {
		middlecards[i] = Card.dealCard().toString();
	}

	public static void reveale() {
		System.out.println("Your Cards: " + playercards[1] + " , " + playercards[2] + "\n");
		System.out.println("Computer's Cards: " + compcards[1] + " , " + compcards[2] + "\n");
		for (int j = 1; j <= 5; j++) {
			System.out.println(middlecards[j]);
		}
		System.out.println();
	}

	public static void rankhand(String[] activecards, String[] middlecards) {
		String[] cards = new String[8];
		int[][] cardsint = new int[8][3];

		cards[1] = activecards[1];
		cards[2] = activecards[2];
		cards[3] = middlecards[1];
		cards[4] = middlecards[2];
		cards[5] = middlecards[3];
		cards[6] = middlecards[4];
		cards[7] = middlecards[5];

		for (int i = 1; i < cards.length; i++) {
			if (cards[i].contains("Two")) {
				cardsint[i][1] = 2;
			}
			if (cards[i].contains("Three")) {
				cardsint[i][1] = 3;
			}
			if (cards[i].contains("Four")) {
				cardsint[i][1] = 4;
			}
			if (cards[i].contains("Five")) {
				cardsint[i][1] = 5;
			}
			if (cards[i].contains("Six")) {
				cardsint[i][1] = 6;
			}
			if (cards[i].contains("Seven")) {
				cardsint[i][1] = 7;
			}
			if (cards[i].contains("Eight")) {
				cardsint[i][1] = 8;
			}
			if (cards[i].contains("Nine")) {
				cardsint[i][1] = 9;
			}
			if (cards[i].contains("Ten")) {
				cardsint[i][1] = 10;
			}
			if (cards[i].contains("Jack")) {
				cardsint[i][1] = 11;
			}
			if (cards[i].contains("Quen")) {
				cardsint[i][1] = 12;
			}
			if (cards[i].contains("King")) {
				cardsint[i][1] = 13;
			}
			if (cards[i].contains("Ace")) {
				cardsint[i][1] = 14;
			}

			if (cards[i].contains("Hearts")) {
				cardsint[i][2] = 1;
			}
			if (cards[i].contains("Daimonds")) {
				cardsint[i][2] = 2;
			}
			if (cards[i].contains("Clubs")) {
				cardsint[i][2] = 3;
			}
			if (cards[i].contains("Spades")) {
				cardsint[i][2] = 4;
			}
		}

		// this artical sort the numbers

		for (int i = 1; i < cardsintsort.length; i++) {
			cardsintsort[i] = cardsint[i][1];
		}
		Arrays.sort(cardsintsort);
		rank = 0;
		value = cardsintsort[7];

		// one and two pairs
		boolean firstpair = false;
		int firstpairvalue = 0;
		for (int i = 1; i < cardsint.length; i++) {
			for (int j = i + 1; j < cardsint.length; j++) {
				if (cardsint[i][1] == cardsint[j][1]) {
					if (firstpair) {
						rank = 2;
						if (cardsint[i][1] > firstpairvalue) {
							value = cardsint[i][1];
						} else {
							value = firstpairvalue;
						}
					} else {
						rank = 1;
						value = cardsint[i][1];
						firstpairvalue = cardsint[i][1];
						firstpair = true;
					}
				}
			}
		}
		// three of a kind
		for (int i = 1; i < cardsint.length; i++) {
			for (int j = i + 1; j < cardsint.length; j++) {
				for (int k = j + 1; k < cardsint.length; k++) {
					if (cardsint[i][1] == cardsint[j][1] && cardsint[i][1] == cardsint[k][1]) {
						rank = 3;
						value = cardsint[i][1];
					}
				}
			}
		}
		// straight
		int count = 1;
		cardsintsort[0] = 0;
		for (int i = 1; i < cardsintsort.length; i++) {
			if (cardsintsort[i] == cardsintsort[i - 1] + 1) {
				count++;
			} else {
				count = 1;
			}
			if (count == 5) {
				rank = 4;
				value = cardsintsort[i];
			}
		}
		// flush
		int C = 0, H = 0, D = 0, S = 0;
		for (int i = 1; i < cardsint.length; i++) {
			switch (cardsint[i][2]) {
				case 1:
					H++;
					break;
				case 2:
					D++;
					break;
				case 3:
					C++;
					break;
				case 4:
					S++;
					break;
			}
			if (H >= 5) {
				int[] cardsh = new int[8];
				value = 0;
				for (int j = 1; j < cardsint.length; j++) {
					if (cardsint[j][2] == 1 && cardsint[j][1] > value) {
						rank = 5;
						value = cardsint[j][1];
					}
					cardsh[j] = cardsint[j][1];
				}
				Arrays.sort(cardsh);
				for (int j = 1; j < cardsh.length; j++) {
					if (cardsh[j] == cardsh[j - 1] + 1) {
						count++;
					} else {
						count = 1;
					}
					if (count == 5) {
						rank = 8;
						value = cardsh[j];
					}
				}
				if (value == 14) {
					rank = 9;
				}
			} else if (D >= 5) {
				int[] cardsd = new int[8];
				value = 0;
				for (int j = 1; j < cardsint.length; j++) {
					if (cardsint[j][2] == 2 && cardsint[j][1] > value) {
						rank = 5;
						value = cardsint[j][1];
					}
					cardsd[j] = cardsint[j][1];
				}
				Arrays.sort(cardsd);
				for (int j = 1; j < cardsd.length; j++) {
					if (cardsd[j] == cardsd[j - 1] + 1) {
						count++;
					} else {
						count = 1;
					}
					if (count == 5) {
						rank = 8;
						value = cardsd[j];
					}
				}
				if (value == 14) {
					rank = 9;
				}
			} else if (C >= 5) {
				int[] cardsc = new int[8];
				value = 0;
				for (int j = 1; j < cardsint.length; j++) {
					if (cardsint[j][2] == 3 && cardsint[j][1] > value) {
						rank = 5;
						value = cardsint[j][1];
					}
					cardsc[j] = cardsint[j][1];
				}
				Arrays.sort(cardsc);
				for (int j = 1; j < cardsc.length; j++) {
					if (cardsc[j] == cardsc[j - 1] + 1) {
						count++;
					} else {
						count = 1;
					}
					if (count == 5) {
						rank = 8;
						value = cardsc[j];
					}
				}
				if (value == 14) {
					rank = 9;
				}
			} else if (S >= 5) {
				int[] cardss = new int[8];
				value = 0;
				for (int j = 1; j < cardsint.length; j++) {
					if (cardsint[j][2] == 4 && cardsint[j][1] > value) {
						rank = 5;
						value = cardsint[j][1];
					}
					cardss[j] = cardsint[j][1];
				}
				Arrays.sort(cardss);
				for (int j = 1; j < cardss.length; j++) {
					if (cardss[j] == cardss[j - 1] + 1) {
						count++;
					} else {
						count = 1;
					}
					if (count == 5) {
						rank = 8;
						value = cardss[j];
					}
				}
				if (value == 14) {
					rank = 9;
				}
			}
		}
		// full house
		int threeofakindvalue;
		for (int i = 1; i < cardsint.length; i++) {
			for (int j = i + 1; j < cardsint.length; j++) {
				for (int k = j + 1; k < cardsint.length; k++) {
					if (cardsint[i][1] == cardsint[j][1] && cardsint[i][1] == cardsint[k][1]) {
						threeofakindvalue = cardsint[i][1];
						for (int l = 1; l < cardsint.length; l++) {
							for (int m = l + 1; m < cardsint.length; m++) {
								if (cardsint[l][1] == cardsint[m][1] && threeofakindvalue != cardsint[l][1]) {
									rank = 6;
									value = threeofakindvalue;
								}
							}
						}
					}
				}
			}
		}
		// four of a kind
		for (int i = 1; i < cardsint.length; i++) {
			for (int j = i + 1; j < cardsint.length; j++) {
				for (int k = j + 1; k < cardsint.length; k++) {
					for (int l = k + 1; l < cardsint.length; l++) {
						if (cardsint[i][1] == cardsint[j][1] && cardsint[j][1] == cardsint[k][1]
								&& cardsint[k][1] == cardsint[l][1]) {
							rank = 7;
							value = cardsint[i][1];
						}
					}
				}
			}
		}

	}

	public static void winner() {

		rankhand(playercards, middlecards);
		prank = rank;
		pvalue = value;
		pcardsintsort = cardsintsort.clone();
		rankhand(compcards, middlecards);
		crank = rank;
		cvalue = value;
		ccardsintsort = cardsintsort.clone();

		System.out.println("Player's rank and value are: " + prank + ", " + pvalue);
		System.out.println("Computer's rank and value are: " + crank + ", " + cvalue);
		System.out.println();

		if (skip) {
			if (foldwin) {
				if (playerwinner) {
					System.out.println("You won that hand. (automatically)");
					playermoney = playermoney + tablemoney;
					tablemoney = 0;
				} else {
					System.out.println("The computer won that hand. (automatically)");
					compmoney = compmoney + tablemoney;
					tablemoney = 0;
				}
			} else {
				if (playerwinner) {
					System.out.println("You won everything.");
					playermoney = playermoney + tablemoney;
					tablemoney = 0;
				} else {
					System.out.println("The computer won everything.");
					compmoney = compmoney + tablemoney;
					tablemoney = 0;
				}
			}
		} else {
			if (prank > crank) {
				pwin();
			} else if (prank < crank) {
				cwin();
			} else {
				if (pvalue > cvalue) {
					pwin();
				} else if (pvalue < cvalue) {
					cwin();
				} else {
					for (int i = (cardsintsort.length - 1); i > 0; i--) {
						if (ccardsintsort[i] > pcardsintsort[i]) {
							cwin();
							break;
						} else if (ccardsintsort[i] < pcardsintsort[i]) {
							pwin();
							break;
						}
					}
				}
			}
		}

		/*
		 * rank 	| 		value 
		 * 0 		| 		2 (if the ranks are equals) 
		 * 1(Pair) 	| 		3 
		 * 2 		| 		4 
		 * 3 		| 		5 
		 * 4 		| 		6 
		 * 5 		| 		7 
		 * 6 		| 		8 
		 * 7 		| 		9 
		 * 8 		| 		10 
		 * 9 		| 		J(11)
		 *  		| 		Q(12)
		 *  		| 		K(13)
		 *  		| 		A(14)
		 */
	}

	private static void cwin() {
		System.out.println("The computer won that hand.");
		compmoney = compmoney + tablemoney;
		tablemoney = 0;
	}

	private static void pwin() {
		System.out.println("You won that hand.");
		playermoney = playermoney + tablemoney;
		tablemoney = 0;
	}

	public static void hand() {
		// 1 hand
		begining();
		display(3);
		turn();
		oneMoreCard(4);
		display(4);
		turn();
		oneMoreCard(5);
		display(5);
		turn();
		reveale();
		winner();

		handbr++;
	}

	public static void main(String[] args) {

		Card cards = new Card();
		cards.shuffle();

		System.out.println("Is good to know, that you have " + playermoney + "$ now \nGo on!");

		while (true) {
			if (playermoney < 5 || compmoney < 5) {
				System.out.print("Bye Bye :P ");
				if (playermoney < 5) {
					System.out.println("The computer won the game!");
				} else if (compmoney < 5) {
					System.out.println("You won the game!");
				}
				break;
			}
			skip = false;
			hand();
			System.out.print("Do you want to continue? yes/no \n");
			String ans = in.next();
			if (!ans.toLowerCase().startsWith("y")) {
				break;
			}
			if (handbr == 5) {
				handbr = 1;
				System.out.print("The deck was shuffled. \n");
				cards.shuffle();
			}
		}
	}
}

/*
 * while (entry) ' { tablemoney = tablemoney + 10; playermoney = playermoney -
 * 5; compmoney = compmoney - 5; begining(); display(3); turn();
 * 
 * hand++; } System.out.println("Do you want to continue ? (yes / no)\n>"); if
 * (in.next() == "yes") { } else { break; } }
 */
/*
 * for (int i=0; i<13;i++) { System.out.printf("%-20s%-20s%-20s%-20s\n"
 * ,Card.dealCard(), Card.dealCard(),Card.dealCard(),Card.dealCard()); }
 */
/*
 * Card.dealCard();
 * 
 * ------------------
 * 
 * 100$ - begining 5$ - entry
 * 
 * ------------------
 * 
 * (cepene) (chips)
 * 
 * ------------------
 * 
 * entry - $ 
 * deal two cards to every player 
 * reveale 3 cards in the middle 
 * turn
 * turn comp. 
 * reveal 1 card in the middle 
 * turn 
 * turn comp. 
 * reveal 1 card in the middle 
 * turn 
 * turn comp. 
 * reveal 
 * define the winner 
 * give the winner the price
 * 
 * ------------------
 * 
 * methodos:
 * 
 * - entry 
 * - first deal (name: begining) 
 * - player turn 
 * - comp. turn 
 * - add one in the middle 
 * - reveal 
 * - define the winner ( royal flush, ... ; if , else) 
 * - give the price
 * 
 * ------------------- end: check -> check raise -> raise -> ... -> call fold =>
 * win all in - fold / all in | !!! if game = started && money = 0 =>
 * pass/check-automaticly
 * 
 * 
 * 
 * 
 * 
 * ------------------
 */

// ascii codes