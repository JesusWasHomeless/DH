Алгоритм:
1.Алиса создаёт простое число p и первообразный корень g по модулю р и передаёт их Бобу

2.Алиса и Боб придумывают секретные числа а и b.

3.Алиса и Боб создают свои открытые числа А = g^a mod p и B = g^b mod p и передают друг другу.

4.Они вычисляют общий ключ К = A^b mod p = B^a mod p. Соответственно, К = g^ab mod p.

5. Ева знает А, B, p, g, но К она не узнает.
