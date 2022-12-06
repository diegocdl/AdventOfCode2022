def get_range_limits(range):
    range_start = range.split('-')[0]
    range_end = range.split('-')[1]
    return int(range_start), int(range_end)

def is_range_contained(range1, range2):
    for num in range1:
        if num in range2:
            return True
    return False

def main():
    pairs_contained = 0
    with open('input.txt') as input_file:
        for line in input_file:
            ranges = line.replace('\n', '').split(',')
            range1_start, range1_end = get_range_limits(ranges[0])
            range2_start, range2_end = get_range_limits(ranges[1])
            range1 = list(range(range1_start, range1_end + 1))
            range2 = list(range(range2_start, range2_end + 1))
            if is_range_contained(range1, range2) or is_range_contained(range2, range1):
                pairs_contained += 1
        print('Result:', pairs_contained)

if __name__ == '__main__':
    main()