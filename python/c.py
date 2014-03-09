# A simple student's information management system

__metaclass__ = type  # Confirm to use the new class
class StudentInfo:  
    # Assigning the student's information
    def setStudentInfo(self, name, age, stuId, scores = [0] * 4):
        self.name, self.age, self.stuId, self.scores = (name, age, stuId, scores)
     
    # Get student's name
    def getName(self):
        return self.name
    # Get student's age
    def getAge(self):
        return self.age
    # Get student;s ID
    def getStuId(self):
        return self.stuId
        
    # Calculate student's average scores
    def average(self):
        return (sum(self.scores)) / 4
 
 # Get the student's information from user   
name, age, stuId = (raw_input("Please enter student's name: "),
                    input("Please enter student's age: "), raw_input("Please enter student's ID: ")) 
# Get the Student's scores from user
scores = []
for i in range(4):
    print "Please enter student's grade %d score: " % (i + 1),
    scores.append(input())

# Creating  a instants of the class of StudentInfo
stu = StudentInfo()
stu.setStudentInfo(name, age, stuId, scores)

# Print student's information
print  stu.getName(), stu.getAge(), stu.getStuId(), stu.average()          