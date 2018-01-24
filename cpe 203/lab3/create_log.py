from random import randint
from random import random
from random import randrange
from random import shuffle

BUY_THRESHOLD = 0.05
customers=['customer' + str(x) for x in range(20)]
products=[('product' + str(x), (x + 1) * 100) for x in range(200)]
sessions=['session' + str(x) for x in range(40)]

def build_starts():
   return ['START {} {}'.format(session, customers[randrange(len(customers))])
      for session in sessions]

def build_ends():
   return ['END {}'.format(session) for session in sessions]

def build_transaction(session):
   product = products[randrange(len(products))]
   if random() < BUY_THRESHOLD:
      return [
         'VIEW {} {} {}'.format(
            session, product[0], product[1]),
         'BUY {} {} {} {}'.format(
            session, product[0], product[1], randint(1, 5))]
   else:
      return ['VIEW {} {} {}'.format(session, product[0], product[1])]


def build_transactions_for(session):
   num_transactions = randrange(10)
   transactions = []
   for _ in range(num_transactions):
      transactions.extend(build_transaction(session))

   return transactions

def build_transactions():
   transactions = []
   for session in sessions:
      transactions.extend(build_transactions_for(session))

   shuffle(transactions)
   return transactions

def print_log():
   print('\n'.join(build_starts()))
   print('\n'.join(build_transactions()))
   print('\n'.join(build_ends()))
   

if __name__ == '__main__':
   print_log()
