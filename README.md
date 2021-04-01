# Irab Al-Ishraf
Irab Al-Ishraf (إعراب الأشراف) is a java application for syntactic positioning for short Arabic sentences based on Natural language processing techniques. 
The application uses Madamira (camel.abudhabi.nyu.edu/madamira/) for morphological analysis and Stanford parser (nlp.stanford.edu/software/lex-parser.shtml) for synthetic analysis.
The app was created as a project for the Arabic Computing course at KFUPM in 2020.

#### Run the program
- To run the program, you need to download the libraries Madamira and Stanford-parser from thier websites above. Then import them to the program.
- Add this line to your JM option: -Xmx2500m -Xms2500m -XX:NewRatio=3

#### Positions that the program supports:
The program supports only these for now (in Arabic):
-	المبتدأ والخبر
-	الأفعال
-	الفاعل والمفعول به
-	المضاف والمضاف إليه
- حروف وأسماء الجر
-	حروف واسماء العطف
-	أسماء الإشارة والموصولة
-	ضمائر الفاعل

