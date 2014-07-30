__author__ = 'loki'

def fibonacci (n):
    if n ==1 or n==2:
        return n;
    else:
        return fibonacci(n-1) + fibonacci(n-2)

class Fibs:
    def __init__(self):
        self.a=0
        self.b=1
    def __next__(self):
        self.a,self.b=self.b,self.a+self.b
        return self.a
    def __iter__(self):
        return self

if __name__ == "__main__":
    print("------------------")

    ab=Fibs()
    c=[]
    for i in ab:
        if i<1000:
            c.append(i)
        else:
            break
    print(c)
    print("------------------")
else:
    for i in range(1,20):
        print(fibonacci(i),end=' , ')