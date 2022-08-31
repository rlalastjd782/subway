import matplotlib.pyplot as plt
import tensorflow.compat.v1 as tf
# from rich.table import Table
from rich.progress import track
from datetime import datetime
from utils import loadDataPandas

tf.disable_v2_behavior()
plt.rc('font', family='Malgun Gothic')

column_names = [
  '사용월',
  '호선명',
  '지하철역',
  '00시-01시 승차인원',
  '00시-01시 하차인원',
  '01시-02시 승차인원',
  '01시-02시 하차인원',
  '02시-03시 승차인원',
  '02시-03시 하차인원',
  '03시-04시 승차인원',
  '03시-04시 하차인원',
  '04시-05시 승차인원',
  '04시-05시 하차인원',
  '05시-06시 승차인원',
  '05시-06시 하차인원',
  '06시-07시 승차인원',
  '06시-07시 하차인원',
  '07시-08시 승차인원',
  '07시-08시 하차인원',
  '08시-09시 승차인원',
  '08시-09시 하차인원',
  '09시-10시 승차인원',
  '09시-10시 하차인원',
  '10시-11시 승차인원',
  '10시-11시 하차인원',
  '11시-12시 승차인원',
  '11시-12시 하차인원',
  '12시-13시 승차인원',
  '12시-13시 하차인원',
  '13시-14시 승차인원',
  '13시-14시 하차인원',
  '14시-15시 승차인원',
  '14시-15시 하차인원',
  '15시-16시 승차인원',
  '15시-16시 하차인원',
  '16시-17시 승차인원',
  '16시-17시 하차인원',
  '17시-18시 승차인원',
  '17시-18시 하차인원',
  '18시-19시 승차인원',
  '18시-19시 하차인원',
  '19시-20시 승차인원',
  '19시-20시 하차인원',
  '20시-21시 승차인원',
  '20시-21시 하차인원',
  '21시-22시 승차인원',
  '21시-22시 하차인원',
  '22시-23시 승차인원',
  '22시-23시 하차인원',
  '23시-24시 승차인원',
  '23시-24시 하차인원',
]
slot_names = column_names[3:]

dataset = loadDataPandas.read('./dataset/subway.csv', column_names)
stations = list(set(dataset['지하철역'].to_numpy()))


class App:
  def __init__(self, station):
    self.__station = station
    self.select_dataset = dataset[dataset['지하철역'] == station][slot_names].to_numpy()
    # placeholder란, 처음에 변수를 선언할 때 값을 바로 주는 것이 아니라, 나중에 값을 던져주는 공간을 만들어주는 것
    # 파라미터를 shape none 으로 크기를 정해지지않음으로 설정, 이렇게 하면, shape이 정해지지 않아서 1차원부터 다 나올 수 있다는 것을 의미
    # 자료를 입력받을 플레이스 홀더를 설정
    self.X = tf.placeholder(tf.float32, shape=None)
    self.Y = tf.placeholder(tf.float32, shape=None)
    #tf.random.uniform은 원하는 형태의 랜덤 값을 가진 배열을 만듬
    # x와 y의 상관관계를 설명하기위한 변수를 -100부터 100까지 균등분포 uniform distribution(연속균등분포)을 가진 무작위 값으로 초기화함
    self.W = tf.Variable(tf.random_uniform([1], -100, 100), 'weight')
    self.b = tf.Variable(tf.random_uniform([1], -100, 100), 'bias')
    self.H = self.X * self.W + self.b
    # x가 주어 졌을떄, y를 만들어 낼수 있는 w와 b를 찾는것, w는 weight, b는 bias.
    # 한쌍 (x,y)의 데이터에 대한 손실값을 계산하는 함수. 실제값과 모델로 예측한 값이 얼마나차이가 나는가를 나타내는값. Cost function 이라고도함 
    # 즉, loss function이 작을수록 그모델이 주어진 x값에 대해 y값을 정확하게 예측한것.
    self.cost = tf.reduce_mean(tf.square(self.H - self.Y))
    # Gradient descent Optimizer 함수를 사용해서 손실값을 최소화 하는 연산 그래프를 생성해야한다.
    # W와 B의 값을 변경해가면서 손실값을 최소화 시키도록 하는데, 함수의 기굴기를 수하고, 기울기가 낮은 쪽으로 계속 이동시키는 알고리즘이다.
    # learning_rate(학습률)을 매개변수로 하는데 이것은 즉, 학습을 얼마나 '급하게' 할것인가에 대한 변수이다.
    # 학습률이 크면 최적의 손실값을 못찾고 지나치게되고, 너무 작으면 학습 속도가 느려진다.
    # 이것을 hyperparameter라고 하는데, 계속 변경해가며 최적의 값을 찾는 것도 중요하다.
    optimizer = tf.train.GradientDescentOptimizer(tf.Variable(0.001))
    self.train = optimizer.minimize(self.cost)
    self.congestion_session = 0
    self.x_data = list(range(24))
    self.y_data = []
    self.slots  = []
    self.getString = lambda x: '0' + str(x) if x < 10 else str(x)
    self.now = 0
  
  #승하차 합 계산 도출 = new_data
  def findCongestionData(self):
    new_data = []
    for data in self.select_dataset:
      layer = []
      for i in range(0, 48, 2):
        ride = data[i]
        quit = data[i + 1]
        layer.append(ride + quit)
      new_data.append(layer)
    return new_data

  #혼잡도 그래프 (plt 변경가능 설정 색상 )
  def drawCongestionGraph(self):
    _data = self.findCongestionData()
    for arr in _data:    plt.plot(arr, 'ro')
    plt.title(f'{self.__station}의 시간대별 혼잡도')
    plt.xlabel('시간대')
    plt.ylabel('혼잡도')
    plt.show()

  # 혼잡도 훈련 모델
  # tf.session (텐서플로 실행창으로 연상결과 확인하기)
  def trainingCongestionModel(self):
    select_dataset = dataset[dataset['지하철역'] == self.__station][slot_names]
    new_data = {}
    session = tf.Session()  # 세션 선문
    session.run(tf.global_variables_initializer()) # 변수 초기화 -> 습과화하는 것이 좋아요
    for i in range(0, 48, 2):
      new_data[slot_names[i][:7]] = sum(
        select_dataset[
          [
            slot_names[i],
            slot_names[i + 1]
          ]
        ].sum().to_numpy()
      ) // len(select_dataset)
    new_data = sorted(new_data.items(), key=lambda x:x[1])
    for i in range(len(new_data)):
      self.y_data.append(new_data[i][1])
      self.slots.append(new_data[i][0])
    for _ in track(range(10000), description="학습 중...", style="white"):
      session.run(
        self.train,
        feed_dict = {
          self.X: self.x_data,
          self.Y: self.y_data
        },
      )
    self.congestion_session = session

  def getNowSlotIndex(self):
    self.now = datetime.now()
    hour = self.now.hour
    return self.slots.index(
      f'{self.getString(hour)}시-{self.getString(hour + 1)}시'
    )

  #혼잡도 예측 테이블 로직
  def predictionCongestion(self):
    if self.congestion_session == 0:  return 0
    i = self.getNowSlotIndex()
    result = self.congestion_session.run(self.H, feed_dict={ self.X: [i] })[0]
    average = sum(self.y_data) // 24
    max_result = max(self.y_data)
    table = Table(show_header=True, header_style="bold yellow")
    table.add_column("현재 시각 ", style="dim", width=12)
    table.add_column("역 ")
    table.add_column("혼잡도 ")
    table.add_column("승·하차 인원 ")
    table.add_column("상태 ")
    table.add_row(
      self.getString(self.now.hour) + ':' + self.getString(self.now.minute),
      self.__station,
      str(round(result / max_result * 100, 5)) + '%',
      str(result if result > 0 else 0),
      "혼잡함" if result > average else "여유로움",
    )

    


def runPython():
	print('파이썬파일 로딩됨')
	input_text = Element("input_text")
	output_text = Element("output_text")
	output_graph = Element("output_graph") 
	
	station = input_text
	if stations.count(station) != 0:
	    app = App(station)
	    app.trainingCongestionModel()
	    # output_text.element.innerText = app.predictionCongestion()
	    output_graph = app.drawCongestionGraph()
	# 그래프를 html로 보내야됨


# 준비
# dataset은 subway.csv를 판다스로 읽음

# 1. 유저에게 역음 입력받고 App() 호출
  # 2. 지하철역 데이터셋에서 이름이 같은행 가져옴
  # 3. 최적화. 학습시킴
  # 4. 그래프 그림 drawCongestionGraph()
  # 5. 내부에서 findCongestionData()호출   //혼잡도(승차객, 하차객 합) 구해줌
