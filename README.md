#Speech Coach

##About
In Toastmasters, people of all backgrounds practice giving speeches (usually 5-7 minutes in length) and will receive a written evaluation (a few notes on one page) and a verbal evaluation (2-3 minutes in length) from another public speaking student. These evaluations vary drastically in content and quality. I have given over one hundred speeches. I have evaluated likely as many. I have won a District 55 Speech Evaluation contest. What I've learned is that having a system is important. Instead of being completely unstructured and random, it is important to know exactly what to look for. Further, it is important to have a system for how to deliver the feedback. In 2-3 minutes it is not possible, not beneficial, to provide in-depth coverage of everything a speaker did well or poorly. Instead, the goal is to make the speaker excited to improve.

The goal of this app is to provide an efficient and complete system for very quickly and thoroughly analyizing a speech and creating useful, encouraging feedback based on it. It is something I wish to use personally, as I feel it can work far better than the multiple pieces of paper I use when evaluating speeches. My goal is to make it so efficient that I can create a full analysis and evaluation while a speaker delivers a 5-7 minute speech. I want it to be a tool that new evaluators can use to quickly create good evaluations, and experienced evaluators can use to provide even better feedback.

More than that, I want to make it a fundamental tool for people learning public speaking--a way they can get somewhat objective feedback about their performances, and see how it improves over time. It should be fundamentally clear where their strengths and best opportunities for improvement are, as well as how they can improve.

##Current State
Only a fraction of the necessary work has been completed. A list of fake evaluations can be viewed. Tap the floating action button to view the most complete screen, that for viewing the different evaluation criteria in their groups. I enjoyed working on the icons and animations here, trying to match Google's "expand/collapse list" guidelines. This screen provides a quick way to view all the different options. Each criterion can be tapped to go to a detail screen--which needs a lot of work--to provide a rating and optional details for that.

There's still plenty of work that needs to be done on these screens, and then the actual generation of an evaluation from the analysis data, saving evaluations, exporting and importing and so on. I'm trying to follow for a Fragment-centric design that can work well on phones or tablets, but have a lot of functionality to implement on the phone before testing and improving the tablet layout.

<img src="https://github.com/chadschultz/speech-coach/blob/master/device-2016-01-25-161330.png" width="360"/>

<img src="https://github.com/chadschultz/speech-coach/blob/master/device-2016-01-25-161601.png" width="360"/>

<img src="https://github.com/chadschultz/speech-coach/blob/master/device-2016-01-25-161633.png" width="360"/>

##Design Philosophy
I am trying to closely follow Material Design, and Android development recommendations in general. It is quite an interesting journey! Google's apps, particularly Gmail, are the biggest design inspiration, as they (mostly) follow the official guidelines.

##Planned Features
The current vision for the completed app is something like this:

###Main screen
Users can import evaluation files, perhaps that have been emailed to her. They can browse through the evaluations, which are grouped into summary rows by speaker. A summary row, if tapped, leads to a similar screen that lists rows for all the evaluations for that speaker. There should be a search bar as well, to search by speaker name, evaluator name, speech title or possibly other information. A specific evaluation row, if tapped, will lead to the list of evaluation criteria. The floating action button can be tapped to create a new evaluation. If there are no evaluations to list, a message should be displayed in the list instead with basic directions on what to do.

###Evaluation Criteria List
The user can expand and collapse criteria groups one at a time (or hit a button to expand or collapse all of them) and browse them to see each criterion and its assigned rating, if any. This allows the user to quickly browse through and either work on criteria in order, or jump to the ones that they want to fill out. Tapping a criteria row leads to a detail screen for viewing and editing that criterion.

###Criterion Detail
Users can, with a single tap, assign a one to five star rating. They can also quickly check boxes for various details that may be useful, such as indicating that a speaker's volume was too soft. This means useful extra information can be provided with just a few taps, although they can also write extra notes if they wish. Additional information about the criterion is displayed to make it clear to less experienced evaluators what behavior justifies a high or low rating. The user can navigate criteria by returning to the evaluation criteria list screen, or by swiping left and right (as Gmail uses to move to the previous or next message). Even on a small phone screen (as opposed to a tablet) this makes it easy for users to completely evaluate a speaker; they could, if desired, just hit the first criterion, tap a star rating, perhaps tap a detail or several, then swipe to the next criterion and repeat.

###Evaluation Outline
A typical outline for providing feedback fills in automatically with some of the highest- and lowest-rated criteria from the speech analysis (unrated criteria are not considered). It would provide a balance of praise and suggestions for improvement, with specific suggestions for how to deliver the feedback--how to phrase a request for improvement in a certain area, or specific coaching phrases that are helpful. Users could follow this outline in delivering a verbal evaluation or coaching. At this point they may hit a share button to email, etc. the results. They can share the full analysis, evaluation outline, or a full text evaluation.

###Evaluation Script
This is much like the evaluation outline, but instead of a simple outline with talking points. Users can edit the script (for this evaluation only), removing, adding or changing lines to suit their own preferences. Users could read or record this verbatim if they wished or, more likely, email it. They may also share from this screen.
