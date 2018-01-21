import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class WTW extends Applet implements ActionListener
{
    Panel p_card; //to hold all of the screens
    Panel intro, picklvl, lvleasy, lvlmed, lvlhard, ins, hscore, easydone, meddone, harddone; //all the different screens (panels)
    CardLayout cdLayout = new CardLayout (); //setting program to implement card layout

    //All the pictures used in the following groups of arrays are from http://4pics1word-answers.com/
    String picseasy[] = {"Drink.jpg", "Dream.jpg", "Basic.jpg", "Right.jpg"}; //This array stores all the pictures that are used for the easy level
    String anseasy[] = {"DRINK", "DREAM", "BASIC", "RIGHT"}; //This array stores the all the answers for the easy level
    String scrambleeasy[] = {"KREIDHWUQANK", "MHSGEUFARSDE", "HFDCSADFIQVB", "XSHDERHFDITG"}; //This array stores the scrambled keyboard that appears on each stage in easy level

    String picsmed[] = {"Choice.jpg", "Letter.jpg", "Minute.jpg", "Record.jpg"}; //This array stores all the pictures that are used for the med level
    String ansmed[] = {"CHOICE", "LETTER", "MINUTE", "RECORD"}; //This array stores the all the answers for the med level
    String scramblemed[] = {"SCHUWIOSECXA", "TRGTELJYLREZ", "GFHUDTSMWNIE", "SBRDSEORCAIZ"}; //This array stores the scrambled keyboard that appears on each stage in med level

    String picshard[] = {"Address.jpg", "Balance.jpg", "Capture.jpg", "Burnout.jpg"}; //This array stores all the pictures that are used for the hard level
    String anshard[] = {"ADDRESS", "BALANCE", "CAPTURE", "BURNOUT"}; //This array stores the all the answers for the hard level
    String scramblehard[] = {"HSYAEWQDRLSN", "CXBVARLATNIE", "IPZDTWCUKERA", "IWOCURAITNRB"}; //This array stores the scrambled keyboard that appears on each stage in hard level

    JLabel piceasy; //globally declaring JLabels for the easy, med, and hard levels which are used to create the image on each level
    JLabel picmed;
    JLabel pichard;

    JButton pickeasy[]; //globally declaring the JButton arrays for all levels which will store the choices that the user is provided with
    JButton pickmed[];
    JButton pickhard[];

    JLabel entereasy[]; //globally declaring JLabel arrays which will display either
    JLabel entermed[]; //5, 6, or 7 (depending on level) dashes (__) indicating that the user has yet to input anything
    JLabel enterhard[]; //they also tell the user the length of the word

    JButton level[] = new JButton [3]; //declaring array which stores the 3 JButtons (easy, medium, hard) which are added to the pick level screen
    //this is declared globally as once the user chooses to return to the main meny after completing a level, the buttons are set disabled

    int queseasy = 0; //declaring numbers for question no. on each of the 3 levels (used to identify which picture the user must be shown)
    int quesmed = 0;
    int queshard = 0;

    String useranseasy = ""; //declaring empty string for each level, which are later manipulated depending on what the user inputs
    String useransmed = "";
    String useranshard = "";

    JLabel scorereasy; //declaring a JLabel which will display the score on each of the 3 levels
    JLabel scorermed;
    JLabel scorerhard;

    int scoreeasy = 0; //declaring a int variable for all three levels which will keep track of score on the levels
    int highscoreeasy = 0; //declaring a int variable for the high score in all three levels (This is important as score != highscore of level if user chooses to replay the level)
    int scoremed = 0;
    int highscoremed = 0;
    int scorehard = 0;
    int highscorehard = 0;

    JLabel hs; //JLabel used to display high score on high score screen
    int highscore; //int variable to keep track of the overall high score (adding the high score of all 3 high score variables)

    int numlvl = 0; //int variable used to keep track of the level the user is on, updated once user picks level (1 - easy, 2 - medium, 3 - hard)

    public void init ()  //beginning of init method
    {
	p_card = new Panel (); //initializing p_card (stores all screens)
	p_card.setLayout (cdLayout); //changing layout to cdLayout

	int count = 0; //declaring variable to keep track of user password attempts
	String pass = "nishant"; //variable storing the correct password
	String guess = ""; //empty string to check if user inputs the correct password

	while (!guess.equals (pass) && count < 5) //run the following code while the user has not guessed the password and has not taken over 5 attempts
	{
	    guess = JOptionPane.showInputDialog (null, "Please enter the password (5 Attempts): ", "Password", JOptionPane.INFORMATION_MESSAGE);
	    //^shows dialog box where user may input password

	    if (guess == null) //if user chooses to click cancel on dialog box
		System.exit (0); //exit the program

	    if (!guess.equals (pass)) //if user does not input the correct password
		count++; //increase attempt count by 1

	    if (count >= 5) //if attempt count get over 5
		System.exit (0); //exit the program

	    else //if user inputs the correct password
	    { //run the game
		screen1 (); //all the screen are called here, so they are displayed when required
		screen2 ();
		screen3 ();
		screen4 ();
		screen5 ();
		screen6 ();
		screen7 ();
		screen8 ();
		screen9 ();
		screen10 ();
		resize (800, 600); //resize the gamescreen to 800 x 600 pixels
		setLayout (new BorderLayout ()); //changing the layout to BorderLayout
		add ("Center", p_card); //adding the main card (with all the screens) to the center of the screen
	    }
	}
    }


    public void screen1 ()  //first screen
    {
	intro = new Panel (new BorderLayout ()); //initializing panel with a border layout
	intro.setBackground (Color.white); //changing background color to white

	JLabel title = new JLabel ("   What's the Word!"); //declaring and initializing the title
	title.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 70)); //setting font of title
	title.setForeground (Color.red); //setting color of title

	JButton menu[] = new JButton [3]; //making a button array to store all the three buttons that are to be displayed
	menu [0] = new JButton ("Play"); //the first button is named the play button
	menu [1] = new JButton ("Instructions"); //the second is the instructions
	menu [2] = new JButton ("High Score"); //and the third being high score

	for (int i = 0 ; i < menu.length ; i++) //for all three buttons
	{
	    menu [i].addActionListener (this); //addActionListeners
	    menu [i].setBackground (Color.red); //make their background color red
	    menu [i].setForeground (Color.white); //make their text color white
	    menu [i].setPreferredSize (new Dimension (230, 60)); //set their size to be 230x60 pixels
	}
	menu [0].setActionCommand ("play"); //make the first buttons actionCommand play
	menu [0].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 45)); //setting the font for the first button
	menu [1].setActionCommand ("instructions"); //make the second buttons actionCommand instructions
	menu [1].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 29)); //setting the font for the second button
	menu [2].setActionCommand ("highscore"); //make the first buttons actionCommand highscore
	menu [2].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 32)); //setting the font for the third button

	Panel buttongrid = new Panel (new GridLayout (1, 3)); //making a 1x3 gridlayout panel to add the buttons together and make sure that they are the same size
	buttongrid.add (menu [0]); //adding all three buttons to the gridlayout
	buttongrid.add (menu [1]);
	buttongrid.add (menu [2]);

	//customized image (created in photoshop) original images used are from imgkid.com
	JLabel mainpic = new JLabel (createImageIcon ("MainPic.jpg")); //declaring and initializing picture used on screen

	p_card.add ("1", intro); //adding the entire screen (intro panel) to p_card as 1
	intro.add ("North", title); //adding the title to top of the intro panel
	intro.add ("Center", mainpic); //adding the picture int the middle of the intro panel
	intro.add ("South", buttongrid); //adding the buttons at the bottom of the intro panel
    }


    public void screen2 ()  //second screen
    {
	picklvl = new Panel (new BorderLayout ()); //initializing panel with a border layout
	picklvl.setBackground (Color.white); //changing background color to white

	JLabel title = new JLabel ("   What's the Word!"); //declaring and initializing the title
	title.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 70)); //setting font of title
	title.setForeground (Color.red); //setting color of title

	level [0] = new JButton ("Easy"); //the first button is named the Easy button
	level [1] = new JButton ("Medium"); //the second button is named the Medium button
	level [2] = new JButton ("Hard"); //the third button is named the Hard button

	for (int i = 0 ; i < level.length ; i++) //for all three buttons
	{
	    level [i].addActionListener (this); //addActionListeners
	    level [i].setBackground (Color.red); //make their background color red
	    level [i].setForeground (Color.white); //make their text color white
	    level [i].setPreferredSize (new Dimension (190, 55)); //set their size to be 190x55 pixels
	}

	level [0].setActionCommand ("easy"); //make the first button's actionCommand easy
	level [0].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 45)); //setting the font for the first button
	level [1].setActionCommand ("med"); //make the second button's actionCommand med
	level [1].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 37)); //setting the font for the second button
	level [2].setActionCommand ("hard"); //make the third button's actionCommand hard
	level [2].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 45)); //setting the font for the third button

	JButton mm = new JButton ("Main Menu"); //Declaring and initializing a main menu button
	mm.addActionListener (this); //add an ActionListener
	mm.setActionCommand ("mm"); //making the button's actionCommand mm
	mm.setPreferredSize (new Dimension (190, 55)); //setting it's size to be 190x55 pixels
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 26)); //changing the font of the text on button
	mm.setBackground (Color.red); //making the background color red
	mm.setForeground (Color.white); //making the color of the text on button white

	Panel levelgrid = new Panel (new GridLayout (1, 4)); //making a 1x4 gridlayout panel to add the buttons together and make sure that they are the same size
	levelgrid.add (level [0]); //adding all  buttons to the gridlayout
	levelgrid.add (level [1]);
	levelgrid.add (level [2]);
	levelgrid.add (mm);

	JLabel mainpic = new JLabel (createImageIcon ("MainPic.jpg")); //declaring and initializing picture used on screen

	p_card.add ("2", picklvl); //adding the entire screen (picklvl panel) to p_card as 2
	picklvl.add ("North", title); //adding the title to top of the picklvl panel
	picklvl.add ("Center", mainpic); //adding the picture to the middle of the picklvl panel
	picklvl.add ("South", levelgrid); //adding the buttons to the bottom of the picklvl panel
    }


    public void screen3 ()  //third screen
    {
	lvleasy = new Panel (); //initializing lvleasy panel
	lvleasy.setBackground (Color.gray); //changing the background of the panel to be gray

	JLabel title = new JLabel ("What's the Word?"); //declaring and initializing a title for the screen
	title.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 55)); //setting the font for the title
	title.setForeground (Color.red); //setting the color for the title

	scorereasy = new JLabel ("                                              Score: " + scoreeasy); //initializing the JLabel which displays score
	scorereasy.setFont (new Font ("Calibri", Font.BOLD, 40)); //setting the JLabels font
	scorereasy.setForeground (Color.red); //setting the JLabels color

	piceasy = new JLabel (createImageIcon ("Drink.jpg"));
	//adding the first image which is to be displayed (changed in the actionPerformed once user answers correctly)

	pickeasy = new JButton [12]; //initializing the pickeasy array (array for making all the buttons with letters which the user may choose from)
	entereasy = new JLabel [5]; //initializing the entereasy array (array for making all the JLabels with display '__' indicating the no. of letters in a word

	Panel a = new Panel (new GridLayout (2, 6)); //declaring and initializing a 2x6 panel for the buttons
	Panel b = new Panel (new GridLayout (1, 5)); //declaring and initializing a 1x5 panel for the JLabels ('__')

	JButton reset = new JButton ("Reset"); //declaring and initializing the reset button
	//this button may be clicked by the user if they decide that they have enter the wrong combination of letters
	reset.addActionListener (this); //adding an actionListener to the button
	reset.setActionCommand ("reseteasy"); //setting actionCommand to be reseteasy
	reset.setPreferredSize (new Dimension (160, 40)); //changing buttons size to 160x40 pixels
	reset.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 32)); //changing the font for the button
	reset.setBackground (Color.red); //making the button's background red
	reset.setForeground (Color.white); //making the button's text color white

	JButton mm = new JButton ("Main Menu"); //Declaring and initializing a main menu button
	mm.addActionListener (this); //adding an ActionListener
	mm.setActionCommand ("mm"); //making the button's actionCommand mm
	mm.setPreferredSize (new Dimension (270, 40)); //setting it's size to be 270x40 pixels
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34)); //changing the font of the text on button
	mm.setBackground (Color.red); //making the background color red
	mm.setForeground (Color.white); //making the color of the text on button white

	for (int i = 0 ; i < 12 ; i++) //for all the 12 buttons (the 'scrambled keyboard')
	{
	    pickeasy [i] = new JButton (scrambleeasy [0].charAt (i) + ""); //make the letter displayed on each button the corresponding character in the array scrambleeasy
	    //for eg. looking at the scrambleeasy array, we see that the first string is "KREIDHWUQANK", so this code, when going through the loop for the first time
	    //takes the character at position 'i' in the string and makes it the character that is displayed on button 'i'
	    pickeasy [i].addActionListener (this); //adding an ActionListener to all the buttons
	    pickeasy [i].setActionCommand ("" + i); //setting the actionCommand of all buttons to be 'i'
	    pickeasy [i].setBackground (Color.red); //setting the background color of each button to be red
	    pickeasy [i].setForeground (Color.white); //setting the text color of each button to be white
	    pickeasy [i].setPreferredSize (new Dimension (50, 35)); //setting the dimensions of each button to be 50x35 pixels
	    pickeasy [i].setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 20)); //setting the font of the text on each button
	    a.add (pickeasy [i]); //adding all the buttons to the gridLayout panel, so they appear in an orderly fashion
	}

	for (int i = 0 ; i < 5 ; i++) //for all the 5 JLabels ('__')
	{
	    entereasy [i] = new JLabel ("__ "); //making all of their text to be "__ "
	    entereasy [i].setForeground (Color.red); //set their color as red
	    entereasy [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 30)); //setting their font
	    b.add (entereasy [i]); //adding them to the gridLayout panel, so they appear in an orderly fashion
	}

	Panel spaces = new Panel (new FlowLayout ()); //declaring a initializing a new panel which uses flowLayout, to add spaces
	JLabel space = new JLabel ("                                                                     "); //declaring and initializing the spaces
	JLabel space1 = new JLabel ("                                                                     ");
	spaces.add (space); //adding the spaces to the panel
	spaces.add (space1);

	Panel d = new Panel (new BorderLayout ()); //creating a panel with borderLayout, which is used to keep all the buttons, dashes together with effective spacing
	Panel c = new Panel (new FlowLayout ()); //creating another panel which will make sure that the empty '__' are displayed right beside the reset button
	//flowLayout is used because it allows you to add things right beside each other
	c.add (b); //adding the '__'s to the flowLayout panel
	c.add (reset); //adding the reset button to the flowLayout panel
	d.add ("North", c); //adding the 'c' panel which contains the '__'s and the reset button to the top of the panel d
	d.add ("Center", spaces); //adding empty JLabels in the center of panel to ensure effective spacing
	d.add ("South", a); //adding all the buttons which the character choices to the bottom of the panel

	JLabel space2 = new JLabel ("     "); //declaring and initializing a space

	lvleasy.add (title); //adding the title to the lvleasy screen
	lvleasy.add (scorereasy); //adding the JLabel which tracks score to the easy screen
	lvleasy.add (piceasy); //adding the picture which contains the 4 pictures that have something in common
	lvleasy.add (d); //adding the d panel which has buttons, '__'s, and reset button all together
	lvleasy.add (space2); //adding a space (empty JLabel)
	lvleasy.add (mm); //adding the main menu button
	p_card.add ("3", lvleasy); //adding the entire screen (lvleasy panel) to p_card as 3
    }


    public void screen4 ()  //fourth screen
    {
	ins = new Panel (); //initializing ins panel
	ins.setBackground (Color.white); //setting color of panel to be white

	JLabel institle = new JLabel ("Instructions"); //declaring and initializing a title for the screen
	institle.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 70)); //setting the font for the title
	institle.setForeground (Color.red); //setting a color for the title

	//image was manually created
	JLabel instext = new JLabel (createImageIcon ("GameInstructions.jpg")); //Creating an image which displays the instructions

	//image was manually created
	JLabel screen = new JLabel (createImageIcon ("GameScreenshot.jpg")); //Creating an image which displays screenshot of the game screen

	JButton mm = new JButton ("Main Menu"); //Declaring and initializing a main menu button
	mm.addActionListener (this); //adding an ActionListener
	mm.setActionCommand ("mm"); //making the button's actionCommand mm
	mm.setPreferredSize (new Dimension (270, 40)); //setting it's size to be 270x40 pixels
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 30)); //changing the font of the text on button
	mm.setBackground (Color.red); //making the background color red
	mm.setForeground (Color.white); //making the color of the text on button white

	JButton play = new JButton ("Play"); //Declaring and initializing a play button
	play.addActionListener (this); //adding an ActionListener
	play.setActionCommand ("play"); //making the button's actionCommand play
	play.setPreferredSize (new Dimension (270, 40)); //setting it's size to be 270x40 pixels
	play.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 50)); //changing the font of the text on button
	play.setBackground (Color.red); //making the background color red
	play.setForeground (Color.white); //making the color of the text on button white

	//creating a JLabel full of empty space
	JLabel space = new JLabel ("                                                                                                                                          ");

	Panel buttons = new Panel (new GridLayout (1, 2)); //creating a 1x2 GridLayout panel for the two buttons created above
	buttons.add (mm); //adding main menu button to the grid layout panel
	buttons.add (play); //adding a play button to the grid layout panel

	ins.add (institle); //adding the title to the panel
	ins.add (instext); //adding the image which with game instructions
	ins.add (screen); //adding the image which displays screenshot of the game screen
	ins.add (space); //adding a empty JLabel to effectively space my widgets
	ins.add (buttons); //adding the main menu and play buttons
	p_card.add ("4", ins); //adding the entire screen (ins panel) to p_card as 4
    }


    public void screen5 ()  //fifth screen
    {
	hscore = new Panel (); //initializing the hscore panel
	hscore.setBackground (Color.white); //setting color of panel to be white

	JLabel hscoretitle = new JLabel ("High Score"); //declaring and initializing a title for the screen
	hscoretitle.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 70)); //setting the font for the title
	hscoretitle.setForeground (Color.red); //setting a color for the title

	JButton mm = new JButton ("Main Menu"); //Declaring and initializing a main menu button
	mm.addActionListener (this); //adding an ActionListener
	mm.setActionCommand ("mm"); //making the button's actionCommand mm
	mm.setPreferredSize (new Dimension (230, 70)); //setting it's size to be 230x70 pixels
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 30)); //changing the font of the text on button
	mm.setBackground (Color.red); //making the background color red
	mm.setForeground (Color.white); //making the color of the text on button white

	//image is custom made, original images from imgkid.com
	hs = new JLabel (createImageIcon ("HighScore0.jpg")); //initializing the image
	//the image displays a high score of 0, when the high score is updated to 1 or 2, the image will also be updated to display a high score of 1 or 2

	hscore.add (hscoretitle); //adding the title to the panel
	hscore.add (hs); //adding the image which with the high score
	hscore.add (mm); //adding the main menu button
	p_card.add ("5", hscore); //adding the entire screen (hscore panel) to p_card as 5
    }


    public void screen6 ()  //sixth screen
    { //this is the screen for the medium level, it uses the exact same code as the easy level except, instead of using variables such as scorereasy or piceasy,
	//they have been replaced by variables such as scorermed or picmed, hence the comments are the exact same.
	//the only exception would be the font sizes or the buttons sizes, and another one which has been commented below
	lvlmed = new Panel ();
	lvlmed.setBackground (Color.gray);

	JLabel title = new JLabel ("What's the Word?");
	title.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 55));
	title.setForeground (Color.red);

	scorermed = new JLabel ("                                              Score: " + scoremed);
	scorermed.setFont (new Font ("Calibri", Font.BOLD, 40));
	scorermed.setForeground (Color.red);

	picmed = new JLabel (createImageIcon ("Choice.jpg"));

	pickmed = new JButton [12];
	entermed = new JLabel [6]; //6 buttons instead of 5 on the easy level
	Panel a = new Panel (new GridLayout (2, 6));
	Panel b = new Panel (new GridLayout (1, 6));
	//instead of being a grid of 1x5 it is 1x6, this is because the medium level displays 6 letter words as compared to the 5 letters of the easy level
	//hence, there must be 6 '__'s instead of 5

	JButton reset = new JButton ("Reset");
	reset.addActionListener (this);
	reset.setActionCommand ("resetmed");
	reset.setPreferredSize (new Dimension (160, 50));
	reset.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	reset.setBackground (Color.red);
	reset.setForeground (Color.white);

	JButton mm = new JButton ("Main Menu");
	mm.addActionListener (this);
	mm.setActionCommand ("mm");
	mm.setPreferredSize (new Dimension (270, 50));
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	mm.setBackground (Color.red);
	mm.setForeground (Color.white);


	for (int i = 0 ; i < 12 ; i++)
	{
	    pickmed [i] = new JButton (scramblemed [0].charAt (i) + "");
	    pickmed [i].addActionListener (this);
	    pickmed [i].setActionCommand ("" + i);
	    pickmed [i].setBackground (Color.red);
	    pickmed [i].setForeground (Color.white);
	    pickmed [i].setPreferredSize (new Dimension (50, 35));
	    pickmed [i].setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 20));
	    a.add (pickmed [i]);
	}

	for (int i = 0 ; i < 6 ; i++) //loop stopping condition is i < 6 instead of the i < 5 on the easy level, this is because there must be 6 spaces (as explained above)
	{
	    entermed [i] = new JLabel ("__ ");
	    entermed [i].setForeground (Color.red);
	    entermed [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 30));
	    b.add (entermed [i]);
	}

	Panel spaces = new Panel (new FlowLayout ());
	JLabel space = new JLabel ("                                                                     ");
	JLabel space1 = new JLabel ("                                                                     ");
	spaces.add (space);
	spaces.add (space1);

	Panel d = new Panel (new BorderLayout ());
	Panel c = new Panel (new FlowLayout ());
	c.add (b);
	c.add (reset);
	d.add ("North", c);
	d.add ("Center", spaces);
	d.add ("South", a);

	JLabel space2 = new JLabel ("     ");

	lvlmed.add (title);
	lvlmed.add (scorermed);
	lvlmed.add (picmed);
	lvlmed.add (d);
	lvlmed.add (space2);
	lvlmed.add (mm);
	p_card.add ("6", lvlmed);
    }


    public void screen7 ()  //seventh screen
    { //this is the screen for the hard level, it uses the exact same code as the easy level except, instead of using variables such as scorereasy or piceasy,
	//it uses variables such as scorerhard or pichard, hence the comments are the exact same.
	//the only exception would be the font sizes or the buttons sizes, and another one which has been commented below
	lvlhard = new Panel ();
	setBackground (Color.gray);

	JLabel title = new JLabel ("What's the Word?");
	title.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 55));
	title.setForeground (Color.red);

	scorerhard = new JLabel ("                                                 Score: " + scorehard);
	scorerhard.setFont (new Font ("Calibri", Font.BOLD, 40));
	scorerhard.setForeground (Color.red);

	JButton reset = new JButton ("Reset");
	reset.addActionListener (this);
	reset.setActionCommand ("resethard");
	reset.setPreferredSize (new Dimension (160, 50));
	reset.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	reset.setBackground (Color.red);
	reset.setForeground (Color.white);

	JButton mm = new JButton ("Main Menu");
	mm.addActionListener (this);
	mm.setActionCommand ("mm");
	mm.setPreferredSize (new Dimension (270, 50));
	mm.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	mm.setBackground (Color.red);
	mm.setForeground (Color.white);

	pichard = new JLabel (createImageIcon ("Address.jpg"));
	pickhard = new JButton [12];
	enterhard = new JLabel [7]; //7 buttons instead of 5 on the easy level
	Panel a = new Panel (new GridLayout (2, 6));
	Panel b = new Panel (new GridLayout (1, 7));
	//instead of being a grid of 1x5 it is 1x7, this is because the hard level displays 7 letter words as compared to the 5 letters of the easy level
	//hence, there must be 7 '__'s instead of 5

	for (int i = 0 ; i < 12 ; i++)
	{
	    pickhard [i] = new JButton (scramblehard [0].charAt (i) + "");
	    pickhard [i].addActionListener (this);
	    pickhard [i].setActionCommand ("" + i);
	    pickhard [i].setBackground (Color.red);
	    pickhard [i].setForeground (Color.white);
	    pickhard [i].setPreferredSize (new Dimension (50, 35));
	    pickhard [i].setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 20));
	    a.add (pickhard [i]);
	}

	for (int i = 0 ; i < 7 ; i++) //loop stopping condition is i < 7 instead of the i < 5 on the easy level, this is because there must be 7 spaces (as explained above)
	{
	    enterhard [i] = new JLabel ("__ ");
	    enterhard [i].setForeground (Color.red);
	    enterhard [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 30));
	    b.add (enterhard [i]);
	}
	Panel spaces = new Panel (new FlowLayout ());
	JLabel space = new JLabel ("                                                                     ");
	JLabel space1 = new JLabel ("                                                                     ");
	spaces.add (space);
	spaces.add (space1);

	Panel d = new Panel (new BorderLayout ());
	Panel c = new Panel (new FlowLayout ());
	c.add (b);
	c.add (reset);
	d.add ("North", c);
	d.add ("Center", spaces);
	d.add ("South", a);

	JLabel space2 = new JLabel ("     ");

	lvlhard.add (title);
	lvlhard.add (scorerhard);
	lvlhard.add (pichard);
	lvlhard.add (d);
	lvlhard.add (space2);
	lvlhard.add (mm);
	p_card.add ("7", lvlhard);
    }


    public void screen8 ()  //eighth screen
    { //screen displayed after easy level is completed
	easydone = new Panel (); //initializing easydone panel
	easydone.setBackground (Color.white); //setting the background color to be white

	JLabel congrats = new JLabel ("Congratulations!"); //declaring and initializing a JLabel as a title
	congrats.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 50)); //setting it's font
	congrats.setForeground (Color.red); //setting the color to be red

	JLabel space = new JLabel ("                                                                 "); //declaring and initializing a JLabel for spacing

	JLabel done = new JLabel ("You have completed the"); //declaring and initializing a JLaebl (part of sentence), the full sentence would not have fit
	//hence there are two sentences
	done.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 38)); //setting it's font
	done.setForeground (Color.red); //setting it's color
	JLabel done1 = new JLabel ("EASY Level! "); //declaring and initializing a JLabel (2nd part of sentence)
	done1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 60)); //setting it's font
	done1.setForeground (Color.green); //setting it's color

	//image from free-designer.net
	JLabel happyman = new JLabel (createImageIcon ("Happyman.png")); //creating an image

	JLabel returntomm = new JLabel ("Please return to the Main Menu"); //declaring and initializing a JLaebl (part of sentence), the full sentence
	//would not have fit, hence there are two sentences
	returntomm.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30)); //setting it's font
	returntomm.setForeground (Color.red); //setting it's color
	JLabel returntomm1 = new JLabel ("and select a different level:"); //declaring and initializing a JLabel (2nd part of sentence)
	returntomm1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30)); //setting it's font
	returntomm1.setForeground (Color.red); //setting it's color

	//the following main menu is different from others, because it has a special actionCommand as when it is clicked, the easy level get's disabled
	//this was neccessary as if the user came back on the level, they would be shown the final image in the array, instead of the first one
	//so, if the user wishes to replay the button, they must click the replay button which has been created below

	JButton mmreplayeasy = new JButton ("Main Menu"); //Declaring and initializing a main menu button
	mmreplayeasy.addActionListener (this); //adding an ActionListener
	mmreplayeasy.setActionCommand ("mmreplayeasy"); //making the button's actionCommand mmreplayeasy
	mmreplayeasy.setPreferredSize (new Dimension (270, 50)); //setting it's size to be 270x50 pixels
	mmreplayeasy.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34)); //changing the font of the text on button
	mmreplayeasy.setBackground (Color.red); //making the background color red
	mmreplayeasy.setForeground (Color.white); //making the color of the text on button white

	JButton replay = new JButton ("Replay"); //Declaring and initializing a replay button
	replay.addActionListener (this); //adding an ActionListener
	replay.setActionCommand ("replayeasy"); //making the button's actionCommand replayeasy
	replay.setPreferredSize (new Dimension (270, 50)); //setting it's size to be 270x50 pixels
	replay.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34)); //changing the font of the text on button
	replay.setBackground (Color.red); //making the background color red
	replay.setForeground (Color.white); //making the color of the text on button white

	Panel grid = new Panel (new GridLayout (1, 2)); //creating a 1x2 GridLayout panel for the two buttons created above
	grid.add (mmreplayeasy); //adding main menu button to the grid layout panel
	grid.add (replay); //adding replay button to the grid layout panel

	easydone.add (congrats); //adding all the widgets created above to the panel 'easydone'
	easydone.add (space);
	easydone.add (done);
	easydone.add (done1);
	easydone.add (happyman);
	easydone.add (returntomm);
	easydone.add (returntomm1);
	easydone.add (grid);
	p_card.add ("8", easydone); //adding the entire screen (easydone panel) to p_card as 8
    }


    public void screen9 ()
    { //this is the screen that is displayed after completing the med screen
	//it uses the exact same code as the screen displayed after easy level
	//exceptions would include some text. eg. completed MEDIUM Level instead of EASY Level, and also the font/button sizes
	//another expection regarding spaces has been commented below
	meddone = new Panel ();
	meddone.setBackground (Color.white);

	JLabel congrats = new JLabel ("Congratulations!");
	congrats.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 50));
	congrats.setForeground (Color.red);

	JLabel space = new JLabel ("                                                                 ");

	JLabel done = new JLabel ("You have completed the");
	done.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 38));
	done.setForeground (Color.red);
	JLabel done1 = new JLabel (" MEDIUM Level! ");
	done1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 60));
	done1.setForeground (Color.green);

	JLabel happymanmed = new JLabel (createImageIcon ("HappyManMed.png"));
	JLabel space1 = new JLabel ("                                                                                             ");
	JLabel space2 = new JLabel ("                                                                                             ");
	JLabel space3 = new JLabel ("                                                                                             ");
	//^additional spaces declared and initialized as the picture was a little smaller than the picture on the easy screen

	JLabel returntomm = new JLabel ("Please return to the Main Menu");
	returntomm.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30));
	returntomm.setForeground (Color.red);
	JLabel returntomm1 = new JLabel ("and select a different level:");
	returntomm1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30));
	returntomm1.setForeground (Color.red);

	JButton mmreplaymed = new JButton ("Main Menu");
	mmreplaymed.addActionListener (this);
	mmreplaymed.setActionCommand ("mmreplaymed");
	mmreplaymed.setPreferredSize (new Dimension (270, 50));
	mmreplaymed.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	mmreplaymed.setBackground (Color.red);
	mmreplaymed.setForeground (Color.white);

	JButton replay = new JButton ("Replay");
	replay.addActionListener (this);
	replay.setActionCommand ("replaymed");
	replay.setPreferredSize (new Dimension (280, 50));
	replay.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	replay.setBackground (Color.red);
	replay.setForeground (Color.white);

	Panel grid = new Panel (new GridLayout (1, 2));
	grid.add (mmreplaymed);
	grid.add (replay);

	meddone.add (congrats);
	meddone.add (space);
	meddone.add (done);
	meddone.add (done1);
	meddone.add (space1);
	meddone.add (happymanmed);
	meddone.add (space2); //additional spaces added to panel
	meddone.add (space3);
	meddone.add (returntomm);
	meddone.add (returntomm1);
	meddone.add (grid);
	p_card.add ("9", meddone);
    }


    public void screen10 ()
    { //this is the screen that is displayed after completing the hard screen
	//it uses the exact same code as the screen displayed after easy level
	//exceptions would include some text. eg. completed HARD Level instead of EASY Level, and also the font/button sizes
	harddone = new Panel ();
	harddone.setBackground (Color.white);

	JLabel congrats = new JLabel ("Congratulations!");
	congrats.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 50));
	congrats.setForeground (Color.red);

	JLabel space = new JLabel ("                                                                 ");

	JLabel done = new JLabel ("You have completed the");
	done.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 38));
	done.setForeground (Color.red);
	JLabel done1 = new JLabel (" HARD Level! ");
	done1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 60));
	done1.setForeground (Color.green);

	JLabel happymanhard = new JLabel (createImageIcon ("HappyManHard.png"));

	JLabel space1 = new JLabel ("                                                                           ");

	JLabel returntomm = new JLabel ("Please return to the Main Menu");
	returntomm.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30));
	returntomm.setForeground (Color.red);
	JLabel returntomm1 = new JLabel ("and select a different level:");
	returntomm1.setFont (new Font ("Copperplate Gothic Bold", Font.BOLD, 30));
	returntomm1.setForeground (Color.red);

	JButton mmreplayhard = new JButton ("Main Menu");
	mmreplayhard.addActionListener (this);
	mmreplayhard.setActionCommand ("mmreplayhard");
	mmreplayhard.setPreferredSize (new Dimension (270, 50));
	mmreplayhard.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	mmreplayhard.setBackground (Color.red);
	mmreplayhard.setForeground (Color.white);

	JButton replay = new JButton ("Replay");
	replay.addActionListener (this);
	replay.setActionCommand ("replayhard");
	replay.setPreferredSize (new Dimension (280, 50));
	replay.setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 34));
	replay.setBackground (Color.red);
	replay.setForeground (Color.white);

	Panel grid = new Panel (new GridLayout (1, 2));
	grid.add (mmreplayhard);
	grid.add (replay);

	harddone.add (congrats);
	harddone.add (space);
	harddone.add (done);
	harddone.add (done1);
	harddone.add (happymanhard);
	harddone.add (space1);
	harddone.add (returntomm);
	harddone.add (returntomm1);
	harddone.add (grid);
	p_card.add ("10", harddone);
    }


    public void moveon ()  //the move on method
    { //this is method that is called once the user correctly guesses any word on any level, it moves the user to the next image, and set of buttons

	useranseasy = ""; //emptying all string variables (for all three levels) which would have stored the user's answer for the previous word
	useransmed = "";
	useranshard = "";

	if (numlvl == 1) //if user is on the easy level
	{
	    queseasy++; //first, the ques variable is increased which is the variable that keeps track of which of the 4 pictures the user is on
	    if (queseasy < 4) //if the ques variable is still below 4 (so, if the user has not yet completed the easy level)
	    {
		piceasy.setIcon (createImageIcon (picseasy [queseasy])); //change the picture to the next picture in the picseasy array
		for (int i = 0 ; i < 5 ; i++)
		{
		    entereasy [i].setText ("__ "); //set all of the '__'s back to their original state (they would have been replaced by letter the user put in)
		}

		for (int i = 0 ; i < 12 ; i++) //display the new set of buttons for the level
		{
		    pickeasy [i].setText (scrambleeasy [queseasy].charAt (i) + ""); //scrambleeasy is the array which stores all the scrambled letters for the level
		} //charAt is used to place a letter on each button
	    }

	    else //if the user has completed the easy level
		cdLayout.show (p_card, "8"); //show the screen which congratulates them and asks to pick between main menu and replay
	}

	else if (numlvl == 2) //if user in on the medium level
	{
	    quesmed++; //first, the ques variable is increased which is the variable that keeps track of which of the 4 pictures the user is on
	    if (quesmed < 4) //if the ques variable is still below 4 (so, if the user has not yet completed the medium level)
	    {
		picmed.setIcon (createImageIcon (picsmed [quesmed])); //change the picture to the next picture in the picsmed array

		for (int i = 0 ; i < 6 ; i++)
		{
		    entermed [i].setText ("__ "); //set all of the '__'s back to their original state (they would have been replaced by letter the user put in)
		}
		for (int i = 0 ; i < 12 ; i++) //display the new set of buttons for the level
		{
		    pickmed [i].setText (scramblemed [quesmed].charAt (i) + ""); //scramblemed is the array which stores all the scrambled letters for the level
		} //charAt is used to place a letter on each button
	    }
	    else //if the user has completed the medium level
		cdLayout.show (p_card, "9"); //show the screen which congratulates them and asks to pick between main menu and replay
	}

	else //if user in on the hard level
	{
	    queshard++; //first, the ques variable is increased which is the variable that keeps track of which of the 4 pictures the user is on
	    if (queshard < 4) //if the ques variable is still below 4 (so, if the user has not yet completed the hard level)
	    {
		pichard.setIcon (createImageIcon (picshard [queshard])); //change the picture to the next picture in the picshard array

		for (int i = 0 ; i < 7 ; i++)
		{
		    enterhard [i].setText ("__ "); //set all of the '__'s back to their original state (they would have been replaced by letter the user put in)
		}
		for (int i = 0 ; i < 12 ; i++) //display the new set of buttons for the level
		{
		    pickhard [i].setText (scramblehard [queshard].charAt (i) + ""); //scramblehard is the array which stores all the scrambled letters for the level
		} //charAt is used to place a letter on each button

	    }
	    else//if the user has completed the hard level
		cdLayout.show (p_card, "10");//show the screen which congratulates them and asks to pick between main menu and replay
	}
    }


    public void highscorescreen () //this is the method which control the high score screen
    {//it is called everytime the user clicks the high score button
    //I have made a different image for each of the possible 13 high scores. 
    //This method replaces the existing image (highscore0), with the appropriate one, depending on user's high score 
	if (highscore == 1)
	    hs.setIcon (createImageIcon ("HighScore1.jpg"));
	else if (highscore == 2)
	    hs.setIcon (createImageIcon ("HighScore2.jpg"));
	else if (highscore == 3)
	    hs.setIcon (createImageIcon ("HighScore3.jpg"));
	else if (highscore == 4)
	    hs.setIcon (createImageIcon ("HighScore4.jpg"));
	else if (highscore == 5)
	    hs.setIcon (createImageIcon ("HighScore5.jpg"));
	else if (highscore == 6)
	    hs.setIcon (createImageIcon ("HighScore6.jpg"));
	else if (highscore == 7)
	    hs.setIcon (createImageIcon ("HighScore7.jpg"));
	else if (highscore == 8)
	    hs.setIcon (createImageIcon ("HighScore8.jpg"));
	else if (highscore == 9)
	    hs.setIcon (createImageIcon ("HighScore9.jpg"));
	else if (highscore == 10)
	    hs.setIcon (createImageIcon ("HighScore10.jpg"));
	else if (highscore == 11)
	    hs.setIcon (createImageIcon ("HighScore11.jpg"));
	else if (highscore == 12)
	    hs.setIcon (createImageIcon ("HighScore12.jpg"));
    }


    public void actionPerformed (ActionEvent e) //actionPerformed method
    {
	if (e.getActionCommand ().equals ("reseteasy"))//if user clicks the reset button on the easy level screen
	{//This button is incase the user enters the wrong combination of letters.
	    for (int i = 0 ; i < 5 ; i++)
		entereasy [i].setText ("__ ");//set all the '__'s back to their original self. 
	    useranseasy = "";//empty the array storing user's answers

	    validate ();//refreshing the screen
	    repaint ();//this was neccessary as the JLabels were malfunctioning and displaying '...'
	}

	else if (e.getActionCommand ().equals ("resetmed"))//if user clicks the reset button on the medium level screen
	{//This button is incase the user enters the wrong combination of letters.
	    for (int i = 0 ; i < 6 ; i++)
		entermed [i].setText ("__ ");//set all the '__'s back to their original self. This is incase the user enters the wrong combination of letters.
	    useransmed = "";//empty the array storing user's answers

	    validate ();//refreshing the screen
	    repaint ();//this was neccessary as the JLabels were malfunctioning and displaying '...'
	}

	else if (e.getActionCommand ().equals ("resethard"))//if user clicks the reset button on the hard level screen
	{//This button is incase the user enters the wrong combination of letters.
	    for (int i = 0 ; i < 7 ; i++)
		enterhard [i].setText ("__ ");//set all the '__'s back to their original self. This is incase the user enters the wrong combination of letters.
	    useranshard = "";//empty the array storing user's answers

	    validate ();//refreshing the screen
	    repaint ();//this was neccessary as the JLabels were malfunctioning and displaying '...'
	}

	else if (e.getActionCommand ().equals ("play"))//if user clicks the play buttons present on intro and instructions screens
	    cdLayout.show (p_card, "2");//redirect them to the pick level screen


	else if (e.getActionCommand ().equals ("easy"))//if the user chooses to play the easy level
	{
	    numlvl = 1;//this level keeps track of the level that the user is playing on
	    cdLayout.show (p_card, "3");
	    resize (600, 800);

	    validate ();
	    repaint ();

	    for (int i = 0 ; i < 5 ; i++)
	    {
		entereasy [i].setText ("__ ");
	    }
	}

	else if (e.getActionCommand ().equals ("med"))
	{
	    numlvl = 2;
	    cdLayout.show (p_card, "6");
	    resize (600, 800);

	    validate ();
	    repaint ();

	    for (int i = 0 ; i < 6 ; i++)
	    {
		entermed [i].setText ("__ ");
	    }
	}

	else if (e.getActionCommand ().equals ("hard"))
	{
	    numlvl = 3;
	    cdLayout.show (p_card, "7");
	    resize (600, 800);

	    validate ();
	    repaint ();

	    for (int i = 0 ; i < 7 ; i++)
	    {
		enterhard [i].setText ("__ ");
	    }
	}
	else if (e.getActionCommand ().equals ("instructions"))
	    cdLayout.show (p_card, "4");

	else if (e.getActionCommand ().equals ("highscore"))
	{
	    cdLayout.show (p_card, "5");
	    highscorescreen ();
	}

	else if (e.getActionCommand ().equals ("mm"))
	{
	    cdLayout.show (p_card, "1");
	    resize (800, 600);

	    validate ();
	    repaint ();
	}

	else if (e.getActionCommand ().equals ("mmreplayeasy"))
	{
	    cdLayout.show (p_card, "1");
	    level [0].setEnabled (false);
	    resize (800, 600);

	    validate ();
	    repaint ();
	}

	else if (e.getActionCommand ().equals ("mmreplaymed"))
	{
	    cdLayout.show (p_card, "1");
	    level [1].setEnabled (false);
	    resize (800, 600);

	    validate ();
	    repaint ();
	}

	else if (e.getActionCommand ().equals ("mmreplayhard"))
	{
	    cdLayout.show (p_card, "1");
	    level [2].setEnabled (false);
	    resize (800, 600);

	    validate ();
	    repaint ();
	}


	else if (e.getActionCommand ().equals ("replayeasy"))
	{
	    cdLayout.show (p_card, "3");
	    numlvl = 1;
	    queseasy = 0;
	    useranseasy = "";
	    scoreeasy = 0;
	    scorereasy.setText ("                                                 Score: " + scoreeasy);
	    piceasy.setIcon (createImageIcon (picseasy [queseasy]));
	    resize (600, 800);

	    for (int i = 0 ; i < 5 ; i++)
	    {
		entereasy [i].setText ("__ ");
		entereasy [i].setForeground (Color.red);
		entereasy [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 20));
	    }

	    for (int i = 0 ; i < 12 ; i++)
	    {
		pickeasy [i].setText (scrambleeasy [queseasy].charAt (i) + "");
	    }
	    validate ();
	    repaint ();
	}

	else if (e.getActionCommand ().equals ("replaymed"))
	{
	    cdLayout.show (p_card, "6");
	    numlvl = 2;
	    quesmed = 0;
	    useransmed = "";
	    scoremed = 0;
	    scorermed.setText ("                                                 Score: " + scoremed);
	    picmed.setIcon (createImageIcon (picsmed [quesmed]));
	    resize (600, 800);

	    for (int i = 0 ; i < 6 ; i++)
	    {
		entermed [i].setText ("__ ");
		entermed [i].setForeground (Color.red);
		entermed [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 20));
	    }

	    for (int i = 0 ; i < 12 ; i++)
	    {
		pickmed [i].setText (scramblemed [quesmed].charAt (i) + "");
	    }
	    validate ();
	    repaint ();
	}

	else if (e.getActionCommand ().equals ("replayhard"))
	{
	    cdLayout.show (p_card, "7");
	    numlvl = 3;

	    queshard = 0;
	    useranshard = "";
	    scorehard = 0;
	    scorerhard.setText ("                                                 Score: " + scorehard);
	    pichard.setIcon (createImageIcon (picshard [queshard]));
	    resize (600, 800);

	    for (int i = 0 ; i < 6 ; i++)
	    {
		enterhard [i].setText ("__ ");
		enterhard [i].setForeground (Color.red);
		enterhard [i].setFont (new Font ("Copperplate Gothic Bold", Font.PLAIN, 20));
	    }

	    for (int i = 0 ; i < 12 ; i++)
	    {
		pickhard [i].setText (scramblehard [queshard].charAt (i) + "");
	    }

	    validate ();
	    repaint ();
	}

	else
	{
	    if (numlvl == 1)
	    {
		int pos = Integer.parseInt (e.getActionCommand ());
		char letter = scrambleeasy [queseasy].charAt (pos);
		useranseasy += letter + "";
		String fake = useranseasy + "___________";
		for (int i = 0 ; i < 5 ; i++)
		    entereasy [i].setText (fake.charAt (i) + " ");
		if (useranseasy.equals (anseasy [queseasy]))
		{
		    scoreeasy++;

		    if (scoreeasy > highscoreeasy)
		    {
			highscoreeasy = scoreeasy;
			highscore = highscoreeasy + highscoremed + highscorehard;
		    }

		    scorereasy.setText ("                                                 Score: " + scoreeasy);
		    JOptionPane.showMessageDialog (null, "You are right", "Correct", JOptionPane.INFORMATION_MESSAGE);
		    moveon ();
		}
		else if (useranseasy.length () == (anseasy [queseasy]).length ())
		{
		    JOptionPane.showMessageDialog (null, "You are incorrect, please try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		}
		validate ();
		repaint ();
	    }


	    if (numlvl == 2)
	    {
		int pos = Integer.parseInt (e.getActionCommand ());
		char letter = scramblemed [quesmed].charAt (pos);
		useransmed += letter + "";
		String fake = useransmed + "___________";
		for (int i = 0 ; i < 6 ; i++)
		    entermed [i].setText (fake.charAt (i) + " ");
		if (useransmed.equals (ansmed [quesmed]))
		{
		    scoremed++;

		    if (scoremed > highscoremed)
		    {
			highscoremed = scoremed;
			highscore = highscoreeasy + highscoremed + highscorehard;
		    }

		    scorermed.setText ("                                                 Score: " + scoremed);
		    JOptionPane.showMessageDialog (null, "You are right", "Correct", JOptionPane.INFORMATION_MESSAGE);
		    moveon ();
		}
		else if (useransmed.length () == (ansmed [quesmed]).length ())
		{
		    JOptionPane.showMessageDialog (null, "You are incorrect, please try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		}
		validate ();
		repaint ();
	    }

	    if (numlvl == 3)
	    {
		int pos = Integer.parseInt (e.getActionCommand ());
		char letter = scramblehard [queshard].charAt (pos);
		useranshard += letter + "";
		String fake = useranshard + "___________";
		for (int i = 0 ; i < 6 ; i++)
		    enterhard [i].setText (fake.charAt (i) + " ");
		if (useranshard.equals (anshard [queshard]))
		{
		    scorehard++;

		    if (scorehard > highscorehard)
		    {
			highscorehard = scorehard;
			highscore = highscoreeasy + highscoremed + highscorehard;
		    }

		    scorerhard.setText ("                                                 Score: " + scorehard);
		    JOptionPane.showMessageDialog (null, "You are right", "Correct", JOptionPane.INFORMATION_MESSAGE);
		    moveon ();
		}
		else if (useranshard.length () == (anshard [queshard]).length ())
		{
		    JOptionPane.showMessageDialog (null, "You are incorrect, please try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		}
		validate ();
		repaint ();
	    }
	}
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = WTW.class.getResource (path);
	if (imgURL != null)
	    return new ImageIcon (imgURL);
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


