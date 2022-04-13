N,M = map(int, input().split())
min_cost = 50000
board = [list(input()) for _ in range(N)]

def cal_min_cost_W_B(board, color):
    cost = 0
    tmp = board.copy()              #board의 첫행 가지고옴

    if board[0] == 'W' and color == 'B':
        cost += 1
        tmp[0] = color
    elif board[0] == 'B' and color == 'W':
        cost += 1
        tmp[0] = color

    tmp[0] = tmp[0].replace(tmp[0], color)             #board의 첫행의 최소 변경 비용이 결정될경우 이후 행들에 대한 비용계산을 위한 연산.
    for i in range(7):
        if tmp[i] == tmp[i+1]:
            if tmp[i] == 'W':
                tmp[i+1] = 'B'
                cost += 1
            elif tmp[i] == 'B':
                tmp[i+1] = 'W'
                cost += 1
    #print('-'*15)            
    #print(tmp, cost)
    #print('-'*15)
    return cost, tmp

def cal_min_cost(board, color):
    cost = 0             #board의 첫행 가지고옴
    board[0] = board[0].replace(board[0], color)             #board의 첫행의 최소 변경 비용이 결정될경우 이후 행들에 대한 비용계산을 위한 연산.
    for i in range(7):
        if board[i] == board[i+1]:
            if board[i] == 'W':
                board[i+1] = 'B'
                cost += 1
            elif board[i] == 'B':
                board[i+1] = 'W'
                cost += 1
    #print('-'*15)
    #print(board, cost)
    #print('-'*15)
    return cost, board

def flip_color(color):
    if color == 'W':
        return 'B'
    elif color == 'B':
        return 'W'

def make_sub_matrix(board, i, j):       #정확
    r,c = 0,0

    sub_maxtrix = [[0] * 8 for _ in range(8)]
    for k in range(i, i+8):
        c = 0
        for l in range(j, j+8):
            sub_maxtrix[r][c] = board[k][l]
            c += 1
        r += 1

    return sub_maxtrix              #8x8 matrix

for i in range((N-8)+1):
    for j in range((M-8)+1):
        l_cost = 0
        r_cost = 0

        init_color_W = 'W'
        init_color_B = 'B'
        sub_board1 = [[0] * 8 for _ in range(8)]                # 변경될 board
        sub_board2 = [[0] * 8 for _ in range(8)]
        sub_board1 = make_sub_matrix(board, i, j)
        sub_board2 = make_sub_matrix(board, i, j)

        if sub_board1[0][0] == 'W' or sub_board1[0][0] == 'B':
            if sub_board1[0][0] == 'W':
                sub_board1[0][0] = sub_board1[0][0].replace(sub_board1[0][0], 'B')
                l_cost += 1
            elif sub_board1[0][0] == 'B':
                sub_board1[0][0] = sub_board1[0][0].replace(sub_board1[0][0], 'W')
                l_cost += 1
        
        pre_color1 = 'Z'
        pre_color2 = 'Z'
        for k in range(8):
            color1 = sub_board1[k][0]
            color2 = sub_board2[k][0]
            
            if k > 0:
                pre_color1 = sub_board1[k-1][0]
                pre_color2 = sub_board2[k-1][0]

            if pre_color1 == color1:
                l_cost += 1
                color1 = flip_color(color1)

            if pre_color2 == color2:
                r_cost += 1
                color2 = flip_color(color2)
            
            c1, tmp = cal_min_cost(sub_board1[k], color1)
            c2, tmp2 = cal_min_cost(sub_board2[k], color2)

            l_cost += c1
            r_cost += c2
            
            #print(i,j,k,l_cost, r_cost, min_cost)

        if l_cost >= r_cost:
            if min_cost >= r_cost:
                min_cost = r_cost
        else:
            if min_cost >= l_cost:
                min_cost = l_cost

print(min_cost)