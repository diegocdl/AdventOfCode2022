import re
def parse_line(line):
    line = line.replace('\n', '')
    index = 0
    elements = []
    while True:
        if index <= len(line):
            element = line[index: index + 4]
            elements.append(element.strip())
        else:
            break
        index += 4
    return elements

def build_stacks(initial_state):
    stacks = {}
    stack_qty = len(initial_state[-1])
    for index in initial_state[-1]:
        stacks[int(index)] = []
    index = -2
    while index >= len(initial_state) * -1:
        for stack_index in range(1, stack_qty + 1):
            element = initial_state[index][stack_index - 1]
            element = element.replace('[', '').replace(']', '').strip()
            if element != '':
                stacks[stack_index].append(element)
        index -= 1
    return stacks

def main():
    initialize = True
    initial_state = []
    stacks = None
    with open('input.txt') as input_file:
        for line in input_file:
            if initialize:
                parsed_line = parse_line(line)
                if len(parsed_line) != 1:
                    initial_state.append(parsed_line)
                else:
                    stacks = build_stacks(initial_state)
                    initialize = False
            else:
                # Asuming the format is move 8 from 3 to 2
                # index = 0 will be number of elements
                # index = 1 will be from stack
                # index = 2 will be to stack
                direction = re.findall('\d+', line)
                direction = list(map(int, direction))
                number_of_elements = direction[0]
                from_stack = direction[1]
                to_stack = direction[2]
                temp = []
                for _ in range(number_of_elements):
                    element = stacks[from_stack].pop()
                    temp.append(element)
                
                for _ in range(number_of_elements):
                    element = temp.pop()
                    stacks[to_stack].append(element)

        result = ''
        for index in range(1, len(stacks) + 1):
            result += stacks[index][-1]

        print('Result:', result)

if __name__ == '__main__':
    main()
