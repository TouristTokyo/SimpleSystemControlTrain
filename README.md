# SimpleSystemControlTrain
## Система контроля движения электропоездов
#
Рассматривается линейный участок железной дороги, соединяющий N
станций (7≤ N ≤ 20). Известно суточное расписание движения электропоездов
между этими станциями (в одном направлении), которое включает M маршрутов
(5≤ M ≤20). Каждый маршрут фиксирует:
<br>
• станцию-пункт отправления и станцию-пункт назначения;
<br>
• промежуточные станции маршрута, в которых электропоезд делает остановку;
<br>
• время прибытия и отправки электропоезда в каждой станции маршрута.
#
Требуется разработать систему контроля движения электропоездов, которая
отслеживает их движение по маршрутам, регистрирует возникающие события и
отклонения движения от расписания, а также корректирует при необходимости
расписание, определяя время предполагаемого прибытия поездов на каждую
станцию маршрута. Можно считать, что электропоезда двигаются по маршрутам
с определенной скоростью (например, 70 км/час).