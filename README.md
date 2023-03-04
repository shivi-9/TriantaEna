# Trianta Ena Game in Java

This is a Java implementation of the Greek variation card game called Trianta Ena, also known as Thirty One. The objective of the game is to accumulate a hand of cards that equals 31 or a hand that has a card value greater than that of the Dealers without exceeding 31.

The game is played by multiple players including the Dealer, referred to as the Banker, and the Players who face the dealer. Each player begins with the same amount of money, and the Banker must begin with three times the amount of the Players.

The game consists of reshuffled continuous rounds of play with the same original deck(s). Each round, the Player may bet any amount of currency up to the total amount they have. If the Player wins, they keep their bet and receive an equal amount of their bet from the Banker. Otherwise, the Player gives the entirety of the bet to the Banker.

To start each round, the Dealer deals one card to each player. The card that the Players are dealt is kept face down and known only to each Player. The first card the Dealer is dealt is kept face up and known to all the Players and the Dealer. After each player receives their first card, they place their bet or choose to fold without betting. Once all bets have been made, each player with a standing bet receives two more cards face up.

After each player has three cards (one down, two up), each Player in turn may take one of the following actions: Hit or Stand. If the player chooses to take another card and their hand is bust, that player must pay the banker the total amount of their bet. Play continues with the next player. Once all the Players stand or have gone bust, the dealer reveals their face down card to the Players and continues to take a hit until the hand value of the Banker reaches or exceeds 27.

The game is played using two standard 52 card decks. Each card has a face value, King, Queen, and Jack have a face value of 10; the Ace can have a face value of 1 or 11. If the hand consists of one Ace, the player can choose to count it as a 1 or an 11. If the hand consists of more than one Ace, only one Ace can count as 1.

There can be multiple winners in each round. The winner of a round is any Player who has a hand value higher than that of the Dealer without having gone bust. In case of a tie, the Dealer wins. A special case includes natural Trianta Ena versus the hand value of a 31. A natural Trianta Ena is defined as a hand having a value of 31 (i.e. an Ace and two face cards). Natural Trianta Ena always triumphs over a value of 31. A natural 31 of the Banker results in the Banker winning the bets from all players.

At the end of each round, the player with the largest total cash amount exceeding that of the Banker is given the option to become the Banker. If they choose to accept, the Player becomes the Banker and current Banker becomes a Player. If they decline, the next greatest amount is given the same option, etc.

This Java implementation of Trianta Ena game includes the following functionalities:

Deal cards to players
Allow players to place bets
Calculate the total value of a player's hand
Determine the winner of each round
Rotate the Banker at the end of each round
To run the game, simply run the Main class.

Enjoy playing Trianta Ena!

