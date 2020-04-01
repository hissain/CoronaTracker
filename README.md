# CoronaTracker
This project is to build application to track the close contacts within last 14 days for a covid-19 positive patient

# Background
Humanity is in crisis due to covid-19 global outbreak which is already declared as a pandemic. This is high time we need to get the best of our technology to solve parts of this global crisis. One of the most difficult challenges for any government of the affected counties is to flatten the curve of infection. Early detection of already affected patients is the most important part to flatten the curve. When a patient is identified as covid-19 positive, government is trying to obtain close contacts information of the patient through manual processes like, by asking to the friends and family of the affected and so on. However, this approach has many limitations in terms of information accuracy, information retrieval delay etc. due to mostly,

1. Falsified information from F&F
2. Hidden information by patient and families to avoid social embarrassment
3. Unknown or missing information of the patient
4. Delayed information retrieval due to manual processes
5. And many more

# Idea: How Technology Can Help?
1. An application can be developed within short time which will be running in most of the popular platforms (Android, iOS).
2. Bangladeshi government need to enforce the installation of this application to all of its citizens as part of the civil duty. Deployment policy and strategy can be devised after more analysis. Operator like, GP, Robi, BL can be doorways.
3. National ID (NID) will be used for registration to the server as the detail of the citizens can be easily extracted by government using NID.
4. All the location along with close contacts data of all the citizens will be stored in a central server.
5. The application will use GPS (Global Positioning System) and other technologies to track the path of user and detect all close contacts of the user and store the histories for last couple of weeks (i.e. >14 days, configurable) in data server.

# Idea: Illustration

Detail architecture can be explored [here](https://github.com/hissain/CoronaTracker/blob/dev/architecture/Architecture.md)
![Conceptual Illutration](https://github.com/hissain/CoronaTracker/blob/master/architecture/ctracker-datascheme.png)

# System Design
Overall system design is available here, 

<img src="https://github.com/hissain/CoronaTracker/blob/master/architecture/SystemDesign.png" alt="Android Registration"/>

Link: [system design](https://github.com/hissain/CoronaTracker/blob/master/architecture/SystemDesign.png)

# How It Works?
We know most of the time we keep our smartphones with us even when we talk, walk, gather, hangout, dine, attend festivals and so on. If CoronaTracker app is ideally installed in the smartphones of all the citizens of the country, close contacts information of a covid-19 positive patient for a certain duration (i.e. more than 2/3 ~ 5/10 mins, configurable) can be easily detected from combination of GPS, Bluetooth and NFC data and logged into server. Thus, if one of the citizens is diagnosed as covid-19 positive, government can find the full list of close contacts with other detail information (i.e. contact durations, distances, times and places). That would of course generate very accurate information to identify the candidates eligible to be quarantined and further immediate testings.

# Core Challenges
1. App Installation - many citizen will have no smartphone hence they will be out of this project. However, that would not be issue because at least a large part of the citizens can be covered with this project.
2. User Privacy - User might not agree to share their locations - However government can create awareness with mobile operations, news media, televisions, gov websites for the sake of humanity. Between health vs. privacy, nobody want to loose health. Moreover, only government will have read access to citizens close contacts only (not all location info) to avoid social chaos and privacy issue.
3. Computation/Storage - Storage as a part of infrastructure will be a challenge. However, we can only keep raw data for last couple of days i.e. ( 14 days data ) into server so that the storage will be linear and easily scalable. Computation can be quickly outsourced to IAS provider, like Amazon/GoogleCloud etc.
4. GPS Inaccuracy - Initial proposal is to use GPS which has poor accuracy, around ~3 meters at best. That might not be feasible to detect close contacts for covid-19. However, if we can consider Bluetooth and NFC along with GPS, accurate information can be archived after creating an appropriate Algorithm.

# Contributors Guidelines
1. Backlogs can be found [here](https://github.com/hissain/CoronaTracker/projects) for Android, iOS and Server developers. You can pick one task from here and start working. This [Youtube tutorial video](https://www.youtube.com/watch?v=e3bjQX9jIBk) might help to the fresh contributors.

2. You can explore detail architecture of draft server data schemes, APIs [here](https://github.com/hissain/CoronaTracker/blob/dev/architecture/Architecture.md). Please explore to understand the current codebase so that it would be easier for you to contribute.

3. If you want to contribute to UI design side, we are waiting for a meaningful logo and thumbs for both Android and iOS application. Please request to us after you create logo/thumb sets.

4. We are also looking for Could EC2 and RDS instance. Anyone can also contribute to this porject by sharing could services for making the POC successful.


# Application Major Screens
<img align="left" src="https://github.com/hissain/CoronaTracker/blob/master/architecture/Screenshots/Android/Screenshot_Registration.png" alt="Android Registration" width="300"/>

<img align="center"  src="https://github.com/hissain/CoronaTracker/blob/master/architecture/Screenshots/iOS/Screenshot_Registration.png" alt="Android Registration" width="245"/>


# Contact
Please feel free to send email to (hissain.khan@gmail.com), if you want to contribute to this project.
