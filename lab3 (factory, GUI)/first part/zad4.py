Symbols = {}  # Rječnik za pohranu vrijednosti simbola

class Expr:
    def __init__(self):
        pass

    def toStr(self):
        pass

    def evaluate(self):
        pass

class Number(Expr):
    def __init__(self, value):
        self.value = value

    def toStr(self):
        return str(self.value)

    def evaluate(self):
        return self.value

class BinaryOp(Expr):
    def __init__(self, operator, left, right):
        self.operator = operator
        self.left = left
        self.right = right

    def toStr(self):
        return f"({self.left.toStr()}{self.operator}{self.right.toStr()})"

    def evaluate(self):
        if self.operator == "+":
            return self.left.evaluate() + self.right.evaluate()
        elif self.operator == "-":
            return self.left.evaluate() - self.right.evaluate()
        elif self.operator == "*":
            return self.left.evaluate() * self.right.evaluate()
        elif self.operator == "/":
            return self.left.evaluate() / self.right.evaluate()

def parseExpression(strinput):
    for operator in ["+-", "*/"]:
        depth = 0
        for p in range(len(strinput) - 1, -1, -1):
            if strinput[p] == ')':
                depth += 1
            elif strinput[p] == '(':
                depth -= 1
            elif depth == 0 and strinput[p] in operator:
                return BinaryOp(strinput[p], parseExpression(strinput[:p]), parseExpression(strinput[p + 1:]))

    strinput = strinput.strip()
    if strinput[0] == '(':
        return parseExpression(strinput[1:-1])
    elif strinput.isnumeric():
        return Number(float(strinput))
    elif strinput.isalpha():
        return Symbol(strinput)
    else:
        raise ValueError("Invalid expression.")

class Symbol(Expr):
    def __init__(self, name):
        self.name = name

    def toStr(self):
        return self.name

    def evaluate(self):
        if self.name in Symbols:
            return Symbols[self.name]
        else:
            raise KeyError(f"Symbol '{self.name}' is not defined.")

# Primjer ispitivanja
tree = parseExpression("6*(x+4)/2-3-x")
print(tree.toStr())  # ((((6.0*(x+4.0))/2.0)-3.0)-x)
# tree.evaluate() izbacuje KeyError jer ne znamo vrijednost x

Symbols['x'] = 5
print(tree.evaluate())  # 19.0

x = 5
print(6 * (x + 4) / 2 - 3 - x)  # 19.0

Symbols['x'] = 4
print(tree.evaluate())  # 17.0