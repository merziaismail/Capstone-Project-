#Name: Marjia Ismail
#Date: 09 March 2021
#Task: 20 Capstone Project
#Compulsory Task Part 1


#This python program allows a user to access/view or change task information. It firstly requires the user to login with the correct credentials. 


#imports datetime function from library 
import datetime

#This gets the current date
date22=datetime.datetime.now()

#Takes the current date and prints it in the format eg. 10 Mar 2021
date_assigned=date22.strftime("%d %b %Y")


#Displays the following to the screen
print("Login")

#Requests user to enter username and password
username=input("Enter your username: ")
password=input("Enter your password: ")


#Opens the user txt file for Read and writing
f=open("user.txt","r+")

#Sets initial states to False
login_successful=False
password_incorrect=False
username_incorrect=False


#Aslong as the login is unsuccessful the following code in the while loop will be executed
while login_successful==False:
    
    for lines in f:                           #Takes the lines in the user text file  
        x=lines.replace(",","")               #Replaces the commas with nothing 
        y=x.split()                           #Then changes it into a list

        if username==y[0]:                    #Checks if username is same as the txt files username
            if password==y[1]:                #Checks if password is same as the txt files password
                print("\nLogin successful!")  #If both conditions true login successful
                login_successful=True
            else:                             #If password is not same as the txt files password sets state to True
                password_incorrect=True
        else:                                 #If the username is not the same as the txt files username sets state to True
            username_incorrect=True           

    if login_successful==False:                                                             #If login is unsuccessful also if password is incorrect then Re enter password
        if password_incorrect==True:
            print("\nLogin failed!\nThe password entered is incorrect.")
            password=input("Re-enter password: ")
        elif username_incorrect==True:                                                      #else if the username is incorrect then enter username and password again
            print("\nLogin failed!\nUsername not found. Please enter a valid username.")
            username=input("\nEnter your username: ")
            password=input("Enter your password: ")
    
    #This offsets the original position to the start of the page so that it continously checks for username and password from the beggining of the file        
    f.seek(0)
        
       
#Set intial state of quit to False        
quit_bool=False


#This function registers users
def reg_user():
    
    #Set Initial state 
    regis_successful=False
    
    #Create an empty list
    storage_list=""
    
    print("\nNew user registration.")
    #Requests user to enter a new username
    username_new=input("\nEnter a new username: ")


    #This loops through the lines of user txt file. With each loop the lines are stored into the empty string storage_list
    for lines in f:
        storage_list=storage_list+lines+"\n"


    #Aslong as the the registration is not successful it will check if the username is in the storage_list if it is the then it will display username already exists and then will ask ask user to enter a new username
    while regis_successful==False:
        if username_new in storage_list:
            #username_new_in=True
            print("The username is already registered!")
            username_new=input("\nEnter a new username: ")
        #Will also check if username not in the storage list. If is not in then will ask user to enter password and re-enter it. regis_successful state changes to True thus the while loop is exited.
        if username_new not in storage_list :
            password_new=input("Enter a new password: ")
            confirm_password=input("Re-enter password to confirm: ")
            regis_successful=True       
    
    #If the new password does not match the password to confirm the user is requested to enter password again
    while password_new!=confirm_password:
        print("\nPasswords do not match!")
        password_new=input("Enter a new password: ")
        confirm_password=input("Re-enter password to confirm: ")
                
    #If both passwords match the the username and password will be appended to the user text file    
    if password_new==confirm_password:
        y=open("user.txt","a")
        y.write("\n"+username_new+", "+password_new)
        y.close()
        print("Username {} has been successfull registered!".format(username_new))



        
#This function allows user to add a new task 
def add_task():

    #Asks the user to enter the following information
    username_task=input("Enter the username of the person the task is assigned to: ")       
    title_task=input("Enter the task title: ")
    description_task=input("Enter the task description: ")
    due_date_task=input("Enter the task due date(eg. format 10 Mar 2021): ")

    #This section appends the above information into the tasks txt file 
    z=open("tasks.txt","a")
    z.write("\n"+username_task+", "+title_task+", "+description_task+", "+date_assigned+", "+due_date_task+", "+"No")
    z.close()
    print("\nTask successfully added!")




def view_all():
    task_number=0
    p=open("tasks.txt","r")    
    for lines in p:                                     #Takes the lines in the tasks txt file 
        split_task_information=lines.split(",")         #Splits the information into a list where there are commas 
        task_number=task_number+1                       #This is for a heading for each task line in the tasks document. With each loop it adds 1 
        print("\nTask Number {}".format(task_number))   #Displays Task number

        #The line below prints out the information neatly with first the task number, then the Task Title, then the Task Description, the date task assigned, date task due, and task completed or not
        print("Task assigned to username: " +split_task_information[0]+"\nTask Title: "+split_task_information[1]+"\nTask Description: "+split_task_information[2]+"\nTask date assigned: "+split_task_information[3]+"\nTask due date:"+split_task_information[4]+"\nTask completed: "+split_task_information[5])

   


def view_mine():
    z=open("tasks.txt","r+")

    #Sets initial states to False
    username_in=False
    reg=False
            
    #Creates an empty string
    task_list_empty=""
    task_titles=""
    task_no=""
 

    #Set initial counter value to -1 so that the indexing and task numbers are related.
    task_no=-1

    #Loop through lines in Tasks text file
    for lines1 in z:
        splitted_lines=lines1.split(",")                #Create a list of the items where they are seperated by commas.
        task_no=task_no+1                               #Adds one with every line
        splitted_lines2=str(task_no)+","+str(lines1)    #Combines the task no with the line in the tasks file 
        splitted_lines3=splitted_lines2.split(",")      #Takes the combined string and changes it into a list
        
            
        #In the for loop it checks if the username is in the tasks text file.If it is then sets username in file as true and then adds the information into the empty list with every loop. Each information from that line containing the name is added into the empty string
        if username==splitted_lines3[1]:
            task_list_empty=task_list_empty+"\nTask number: "+splitted_lines3[0]+"\nTask Title: "+splitted_lines3[2]+"\nTask Description:" +splitted_lines3[3]+"\nTask date assigned: " +splitted_lines3[4]+"\nTask due date: "+splitted_lines3[5]+"\nTask Completed:"+splitted_lines3[6]+"\n"
            #task_titles=task_titles+"\n"+str(task_no)+splitted_lines[1]
            username_in=True     

    #Setting initial states
    complete=True
    edit_task=True
    task_no1=0

    #Create empty strings
    string=""
    string2=""   
    string20=""
    final_string=""
            
    #If username in file is true. The tasks that were stored in the empty list is printed/displayed
    if username_in==True:
        print("\nThe tasks assigned to username {} are:".format(username))
        print(task_list_empty)

        #The user then will be requested to enter the taks number they want to edit or can enter -1 to goto the main menu
        task_selection=int(input("Enter the task number you wish to edit or enter -1 to return to the main menu.\n{}\nEnter your selection: ".format(task_titles)))

        #If the user enters -1 the user will be directed to the main menu
        if task_selection==-1:
            print()

        #However if the user enters any number other than -1 the user is asked what type of edit they want to make
        elif task_selection!=-1:
            print("What would you like to do:\n1-mark the task as complete\n2-Edit the task")
            edit_selection=int(input("Enter your selection: "))

            #if user enters 1 
            if edit_selection==1:
                s=open("tasks.txt","r+")            #Open up the tasks file.
                v=s.readlines()[task_selection]     #Read the line that the user entered(The index represents the line number).
                w=v.replace('No','Yes')             #The No is replaced with yes.
      
                u=open("tasks.txt","r+")            
                for lines2 in u:                    #Run through the lines in the tasks file.
                    strip_lines=lines2.split(",")   #Change the lines into a list.
                    if v!=lines2:                   #If the line that the user requested to edit is not the same as one of the lines the task file.
                        string2=string2+lines2      #Then must store all those lines into an empty string besides the task that needs to be edited.
                
                g=open("tasks.txt","w")             #This writes all the lines to the txt file
                g.write(string2+"\n"+w)
                print("\nThe tasks has been successfully changed to completed!")

            #If the user selects 2  
            elif edit_selection==2:                                         
                e=open("tasks.txt","r+")                                
                check_completion=e.readlines()[task_selection]          #Opens the tasks file and reads the line that the user selected to edit
                check_completion1=check_completion.replace("\n","")     #Removes the \n from the line
                check_completion2=check_completion1.split(",")          #Changes it into a list

                #Checks if the task has been completed if complete then the task cant be edited
                if check_completion2[5]==" Yes":                                                            
                    print("Task cannot be edited!")

                #But if the task is not complete     
                elif check_completion2[5]==" No":

                    #The user will be asked the type of edit they want to make
                    print("What would you like to do:\n1-Edit the username\n2-Edit the due date")           
                    edit_selection_item=int(input("Enter your selection: "))

                    #If the user wants to edit the username
                    if edit_selection_item==1:
                          new_name_assigned=input("Enter the new name you want to assign the task to: ")  #Asks user to enter the name they want to change it to
                          check_completion2[0]=new_name_assigned                                          #Stores the new name into the index zero where the previous name was 
                          empty_check_completion2=""                                                      #Create an empty list 
                          string_check_completion2=",".join(check_completion2)                            #The string that the user wants to edit was a list to it changed back into a list with the commas included
                          
                          ss=open("tasks.txt","r+")                            
                          vv=ss.readlines()[task_selection]             #Opens the tasks file and reads the line that the user selected to edit
                          string22=""                                   #Creates an empty list

                          uu=open("tasks.txt","r+")                  
                          for lines22 in uu:                            #Run through the lines in the tasks file.
                              strip_lines=lines22.split(",")            #Change the lines into a list.
                              if vv!=lines22:                           #If the line that the user requested to edit is not the same as one of the lines the task file.
                                  string22=string22+lines22             #Then must store all those lines into an empty string besides the task that needs to be edited.
                          
                          gg=open("tasks.txt","w")                      
                          gg.write(string22)                            #This writes all the lines to the txt file
                          gg.close()

                          yy=open("tasks.txt","a")                      #Then appends the edited line to txt file
                          yy.write("\n"+string_check_completion2)
                          yy.close()
                          print("\nThe task username has been successfully changed!")

                    #But if the task is not complete to eidt the due date      
                    elif edit_selection_item==2:
                        new_due_date=input("Enter the new due date (eg format- 10 Mar 2021): ")   #Request user to enter the new date 
                        check_completion2[4]=" "+new_due_date                                           #Stores the new name into the index zero where the previous due date was stored

                        empty_check_completion2=""
                        string_check_completion2=",".join(check_completion2)                        #The string that the user wants to edit was a list to it changed back into a list with the commas included

                        ss=open("tasks.txt","r+")
                        vv=ss.readlines()[task_selection]                                           #Opens the tasks file and reads the line that the user selected to edit
                     
                        string222=""

                        uuu=open("tasks.txt","r+")
                        for lines22 in uuu:                                                         #Run through the lines in the tasks file.
                            strip_lines=lines22.split(",")                                          #Change the lines into a list.
                            if vv!=lines22:                                                         #If the line that the user requested to edit is not the same as one of the lines the task file.
                                string222=string222+lines22                                         #Then must store all those lines into an empty string besides the task that needs to be edited.
            
                          
                        ggg=open("tasks.txt","w")                                         
                        ggg.write(string222)                        #This writes all the lines to the txt file  
                        ggg.close()

                        
                        yyy=open("tasks.txt","a")
                        yyy.write("\n"+string_check_completion2)    #Then appends the edited line to txt file                                
                        yyy.close()
                        print("\nThe task due date has been successfully changed!")
    
    else:
    #If the username is not in the list splitted_lines
        print("\nThere are no tasks assigned to username: {}.".format(username))

from datetime import datetime   #Import the function from library so that the function can be used




def generate_reports():         

    ##******Task OverView*****##

    #Setting initial values and states 
    task_count=0
    complete_count=0
    incomplete_count=0
    overdue_counter=0
    not_overdue_counter=0
    due_bool=False
    incomplete_bool=False

    tss=open("tasks.txt","r+")                          #Open task for reading and writing
    for lines12 in tss:                                 #Loop through the lines in the txt files
        splitted_lines=lines12.split(",")               #Create a list of the items where they are seperated by commas.
        task_count=task_count+1                         #counts the lines
        if splitted_lines[5]==(" Yes\n" or " Yes"):     #if in the lines if the task is completed 
            complete_count=complete_count+1             #counts the number of tasks that are completed
        else:
            incomplete_count=incomplete_count+1         #counts the number of tasks that are not completed

            
    tsss=open("tasks.txt","r+")                                         #Open task for reading and writing
    for lines122 in tsss:
        splitted_lines2=lines122.split(",")                             #Create a list of the items where they are seperated by commas.
        
        due_date=datetime.strptime(splitted_lines2[4]," %d %b %Y")      #Takes the due date in each line and changed the format to eg 11/03/2021
        due_date2=datetime.strftime(due_date,"%d/%m/%Y")

        date_current=datetime.now()                                     #Gets the current date
        date_current2=datetime.strftime(date_current,"%d/%m/%Y")        #changes its format to eg 11/03/2021

        dates_subtract=due_date-date_current                            #Subtract the two dates 
        dates_subtract_3=dates_subtract.days                            #Stores the number of days into the varible
        #print(dates_subtract_2)
        
        if (dates_subtract_3<=0):                                       #If the days is a negative value
            if splitted_lines2[5]!=(" Yes\n" or " Yes"):                #if the task is not completed 
                overdue_counter=overdue_counter+1                       #the add 1 to the overdue counter 
            else:
                not_overdue_counter=not_overdue_counter+1               #Otherwise then add 1 to the not_overdue counter 

   
    percentage_overdue=(overdue_counter/task_count)*100                 #Calculate the percentage by dividing the number of tasks overdue by the total number of tasks and then multiplying it by 100
    percentage_overdue_int=int(percentage_overdue)                      #Changing the value into an integer

    

    percentage_incomplete=(incomplete_count/task_count)*100             #Calculate the percentage by dividing the number of tasks incomplete by the total number of tasks and then multiplying it by 100
    percentage_incomplete_int=int(percentage_incomplete)                #Changing the value into an integer


    #The code below writes all the information into the task_overview txt file
    ts1=open("task_overview.txt","w")
    ts1.write("\tTask Overview Report\n")
    ts1.write("\nThe total number of Tasks are: "+ str(task_count))
    ts1.write("\nThe total number of complete Tasks are: "+ str(complete_count))
    ts1.write("\nThe total number of incomplete Tasks are: "+ str(incomplete_count))
    ts1.write("\nThe total number of tasks that are overdue: "+ str(overdue_counter))
    ts1.write("\nThe percentage of tasks that are incomplete: "+ str(percentage_incomplete_int)+"%")
    ts1.write("\nThe percentage of tasks that are overdue: "+ str(percentage_overdue_int)+"%")
    ts1.write("\n")



    ##******User OverView*****##

    #Setting initial values and states 
    user_count=0
    task_count2=0
    users=""
    namefreq=""
    split_names_string=""
    
    us=open("user.txt","r+")
    for lines1 in us:
        splitted_lines=lines1.split(",")                        #Create a list of the items where they are seperated by commas.
        user_count=user_count+1                                 #Counts the number of users registered
        task_count2=task_count2+1                               #Counts the number of tasks
        count_user=splitted_lines.count(splitted_lines[0])      #
        users=users+splitted_lines[0]+"\n"                          
        namefreq=namefreq+splitted_lines[0]+" "                 #Stores the usernames in the empty string 

    #Changes the string containing the usernames into a list 
    users_list=namefreq.split()                             

    #Opens the respective text files
    f_task=open("tasks.txt","r+")
    f_user=open("user.txt","r+")

    #Setting initial counter value to zero 
    count_usernames=0    
    count_usernamess=0

    
    date_current22=datetime.now()                                     #Gets the current date
    date_current222=datetime.strftime(date_current22,"%d/%m/%Y")      #changes its format to eg 11/03/2021

    #Counts the amount of users registered
    for usernamess in f_user:
        count_usernamess+=1

    #Write the following information to the user overview txt file    
    u=open("user_overview.txt","w")
    u.write(f"\tUser Overview Report\n")
    u.write(f"\nThe total number of users registered: {count_usernamess}")
    u.write(f"\nThe total number of tasks generated: {task_count}")
    


    #The users_list contains the users txt file information which has already been split
    for names in users_list:

        #Goes back to start at the start of the tasks txt file
        f_task.seek(0)

        #Setting initial counter value to zero
        count_complete=0
        count_incomplete=0
        count_tasks_no=0
        count_tasks=0
        overdue_counter1=0
        not_overdue_counter1=0


        #Loop through the tasks txt file
        for string in f_task:
            split_names_replace5=string.replace("\n","")                    #Removes the \n in the lines 
            split_names5=split_names_replace5.split(",")                    #Changes the lines into lists
            count_tasks=count_tasks+1                                       #Counts the number of tasks 


            due_date22=datetime.strptime(split_names5[4]," %d %b %Y")       #Takes the due date in each line and changed the format to 11/03/2021
            due_date222=datetime.strftime(due_date22,"%d/%m/%Y")                  

            dates_subtract2=due_date22-date_current22                       #Subtracts the due date by the current date 
            dates_subtract_2=dates_subtract.days 

           
            if names ==split_names5[0]:                                     #If the names in the user txt file matches the names in the tasks text file at index 0
                count_tasks_no+=1                                           #Start counting the number of tasks per user
                
                if split_names5[5]==" Yes":                                 #If the task is completed 
                    count_complete+=1                                       #start counting the number of completed tasks
                    
          
                elif split_names5[5]==" No":                                #If the is not complete 
                    count_incomplete+=1                                     #start counting the number of incomplete tasks
                    if dates_subtract2.days<=0:                             #If the due date minus the current date is a negative value. It means that its a overdue task
                        overdue_counter1=overdue_counter1+1                 #Start counting the number of tasks that overdue
    
                    
        if count_tasks_no>0:                                                            #Only perform these calculations if there are tasks for that username
            percentage_completed=int((count_complete/count_tasks_no)*100)               #Counts the complete tasks percentage by dividing the total number of tasks completed by the total tasks then multilpied by 100
            percentage_incomplete=int((count_incomplete/count_tasks_no)*100)            #Counts the incomplete tasks percentage by dividing the total number of tasks that are incomplete by the total tasks then multilpied by 100
            percentage_overdue=int((int(overdue_counter1)/int(count_tasks_no))*100)     #Counts the overdue tasks percentage by dividing the total number of tasks overdue by the total tasks then multilpied by 100  
                
                      
        elif count_tasks_no==0:            #If there are no tasks for a spesific user the set values below to zero                                             
            percentage_completed="0"
            percentage_incomplete="0"
            percentage_overdue="0"

    
        #Writes the following information into the the User overview txt file
        u.write("\n")    
        u.write(f"\nThe total number of Tasks for {names} is: {count_tasks_no}")
        u.write(f"\nThe total number of Tasks that are complete are: {count_complete} ")
        u.write(f"\nThe total number of Tasks that are incomplete are: {count_incomplete} ")
        u.write(f"\nThe percentage of tasks assigned that has been completed: {percentage_completed}% ")
        u.write(f"\nThe percentage of tasks assigned that must still be completed: {percentage_incomplete}% ")
        u.write(f"\nThe percentage of tasks assigned that have not yet been completed and are overdue: {percentage_overdue}% ")



        
def display_stats():
    print()                             #Adds a space
    generate_reports()                  #calls function generate reports
    uu=open("task_overview.txt","r+")   #Opens Task_overview txt file 
    print(uu.read())                    #Reads all the information and prints it to the console

    print()                             #Adds a space
    generate_reports()                  #calls function generate reports
    u=open("user_overview.txt","r+")    #Opens Task_overview txt file 
    print(u.read())                     #Reads all the information and prints it to the console




def quit_menue():
    if selection=="e":
        quit_bool=True
        login_successful=False   




#Aslong as the login is successful and the quit option is not selected the selection menue will be displayed
while login_successful==True and quit_bool==False:
    print()
    print("Please select one of the following options:")
    print("r-register user \na-add task \nva-view all tasks\nvm-view my tasks\ngr-generate reports\nds-display statistics \ne-exit")
    selection=input("\nEnter your selection here: ")

    #Checks if the user entered r. If yes the user will be requested to enter a new username, password and again password conformation.   
    if selection== "r":
        reg_user()

    #Checks if the user entered a. If yes the user will be requested to enter the information below and then that information will be appended into the tasks text file         
    elif selection== "a":
        add_task()

    #Checks if the user entered va. 
    elif selection== "va":
        view_all()
    
    #Checks if the user selected e. If true the quite_bool state changed to True thus the while loop condition is not met therefore the menue is no longer displayed    
    elif selection=="e":
        quit_menue()                #Call function
        print("Have a Good day!")
        login_successful=False
        quit_bool=True
        
    #Checks if login successful and selection is view mine
    elif selection== "vm":
        view_mine()                 #Call function
        
    #Checks if login successful and selection is generate results
    elif selection== "gr":
        generate_reports()          #Call function

    elif selection== "ds":
        generate_reports()          #Call function    
        display_stats()             #Call function
        
    else:
        print("Invalid selection!")
        
        
    

                    
                

            
            
            
            
            
            
        


        
    

        
