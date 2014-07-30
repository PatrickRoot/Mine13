__author__ = 'loki'
'''
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
'''
class NumSum:

    def count_result(self,num):
        result = 0;
        for i in range(0,num):
            if(self.is_3_or_5(i)):
                result += i;
        return result;

    def is_3_or_5(self,i):
        if(i%3 ==0 ):
            return  True
        elif(i%5 ==0):
            return  True
        return False

if __name__ == "__main__":
    a = NumSum()
    print(a.count_result(1000))