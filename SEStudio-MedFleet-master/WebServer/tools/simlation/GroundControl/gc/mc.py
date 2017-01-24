def get_socket():
    host = '127.0.0.1'
    port = 20005
    size = 1024
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host,port))

def send_ready():
    while readyFlag == 0:
        print('ready')
        s.send((data_string.encode('utf-8')))
        time.sleep(1)

## todo break into two one get get data into json one send New thread to send data to MC
def send_attributes():
    print('...Sending Updates to MC...')
    while isNotFlying == 0:
        drone_info = jsonInfo()
        s = get_socket()
        s.send((data_string.encode('utf-8')))
        time.sleep(1)
        
