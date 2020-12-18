create or replace procedure INCRE(N in EMP.EMPNO%TYPE, R in NUMBER)
is 
   newPay NUMBER;
begin
   select SAL into newPay from EMP where EMPNO=N;
   newPay := newPay + newPay * R;
   update EMP set SAL=newPay where EMPNO=N;
   commit;
end;
/

-- call incre(7369, 0.1);
-- select EMPNO, SAL from EMP2 where EMPNO=7369;