# CoronaTracker
This project is to build application to track the close contacts within last 14 days for a covid-19 positive patient 

# Background
Humanity is in crisis due to covid-19 global outbreak which is already declared as a pandemic. This is high time we need to get the best of our technology to solve parts of this global crisis. One of the most difficult challenges for any government of the affected counties is to flatten the curve of infection. Early detection of already affected patients is the most important part to flatten the curve. When a patient is identified as covid-19 positive, government is trying to get close contacts information of the patient following manual steps like, by asking to the friends and family of the affected and so on. However, this approach has many limitations in terms of information accuracy, information retrieval delay etc. due to mostly,

1. Falsified information from F&F
2. Hidden information by families to avoid social embarrassment
3. Unknown or missing information of the patient
4. Delayed information retrieval due to manual processes
5. And many more

# Idea: How Technology can help?
1. An application can be developed within short time which will be running in most of the popular platforms (Android, iOS).
2. Bangladeshi government need to enforce the installation of this application to all its citizen as part of civil duty. Deployment policy and strategy can be devised after more analysis. Operator like, GP, Robi, BL can be useful.
3. All the close contact data of all citizen will be stored in a central or distributed server.
4. The application will use GPS (Global Positioning System) to detect all the loose contacts and close contacts of the citizens and store the histories in data server for last couple of weeks (i.e. >14 days, configurable).

# Idea: Illustration

![Conceptual Illutration](https://github.com/hissain/CoronaTracker/blob/master/architecture/ctracker-datascheme.png)

# How It Works?
We know most of the time we keep our smartphones with us even when we talk to other, gather, hangout, dine and attend festivals. If CoronaTracker app is ideally installed in the smartphones of all the citizens of the country, close contacts of a covid-19 positive patient for a certain duration (i.e. more than 2/3 ~ 5/10 mins) can be easily detected by GPS/BL/NFC and immediately logged into server. Later, if one of the citizens is identified as covid-19 positive, government can find the full close contacts tree linkages with other detail information like contact durations, distances, times and places. That would be off course generate very accurate information for the government to identify the candidates eligible to quarantine and further immediate testings. 

# Core Challenges
1. App Installation - many citizen will have no smartphone hence they will be out of this project. However, that would not be issue because at least a large part of the citizens can be covered with this project.
2. User Privacy - User might not agree to share their locations - However government can create awareness with mobile operations, news media, televisions, gov websites for the sake of humanity. Between health vs. privacy, nobody want to loose health.
3. Storage - Storage as a part of infrastructure will be a challenge. However, we can only keep raw data for last couple of days i.e. ( 14 days data ) into server so that the storage will be lenear and easily scalable.

# Contact
Please feel free to send email to (hissain.khan@gmail.com), if you want to contribute to this project.
