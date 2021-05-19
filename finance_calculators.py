#Name: Marjia Ismail
#Date: 04 February 2021
#Task: 12
#Capstone Project 1

#This program is used to calculate two financial calculations either Investment or Home Loan.

#Allows access to maths functions library.
import math

#Request users input and Stores input into variable
calculation_selection=input("Choose either 'investment' or 'bond' from the menu below to proceed:\n\n investment \t- to calculate the amount of interest you'll earn on interest \n bond \t \t- to calculate the amount you'll have to pay on a home loan \n:")

#CaseFold function is used to ignore the case from the users input.
#If the user enters investment the user will be asked three questions which will be stored in its respective variables.
if calculation_selection.casefold() == "investment":
    deposit=int(input("Enter the amount you wish to deposit: "))
    interest_percentage=int(input("Enter the percentage interest rate: "))
    years=int(input("Enter the number of years you plan on investing for: "))

    #Then the user will then be asked if simple or compound interest.
    interest=input("Input whether you would like simple or compound interest: ")

    #If user enteres simple
    #The simple interest calculation will be munipulated and then be displayed.
    if interest.casefold()=="simple":
       simple_interest=deposit*(1+(interest_percentage/100)*years)
       print("The total investment on simple interest is R{}".format(str(simple_interest)))

        
    #Another condition is checked if user inputs compound.
    #If true the compound interest calculation will be munipulated and then be displayed.
    elif interest.casefold()=="compound":
         compound_interest=deposit*math.pow((1+(interest_percentage/100)),years)
         print("The total investment on compund interest is R{}".format(str(round(compound_interest,2))))


    #If simple or compound in not entered by the user then an error message will apear.         
    else:
                print("Invalid response! Select compound or simple interest")

#CaseFold function is used to ignore the case from the users input.
#From the 1st IF statment If the user enters bond the user will be asked three questions which will be stored in its respective variables.   
elif calculation_selection.casefold() == "bond":
     present_value=int(input("Enter the present value of the house: "))
     interest_rate=int(input("Enter the interest rate: "))
     loan_repayment=int(input("Enter the number of months for bond repayment: "))
     
     #Then the bond repayment calculation will be munipulated and then be displayed.
     bond_repayment=((interest_rate/12)*present_value)/(1-math.pow((1+(interest_rate/12)),(-1)*(loan_repayment)))
     print("The total bond repayment each month is R{}".format(str(round(bond_repayment,2))))
     
#If non of the conditions are met where the user does not type in investement or bond the an error message will be displayed    
else:
    print("You have entered an invalid response, please re-enter the type of calculation you would like to do.")
    
    
