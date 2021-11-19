select * from employees where employee_id in (100,102,105,200); 

select * from employees where job_id = 'SA_REP';

select * from employees where job_id like  'SA%';

select * from employees;

select *  from employees where (DEPARTMENT_ID=20 or department_id=30) and salary > 10000;


select * from employees order by employee_id, first_name desc;



select first_name, salary from employees where rownum=2 order by salary desc

select first_name, salary from employees where salary < (select max(salary) from employees) and rownum=1 

select first_name,salary from employees order by salary desc

SELECT TOP 1 SALARY
FROM (
SELECT DISTINCT TOP 3 SALARY
FROM employees
ORDER BY SALARY DESC
) RESULT
ORDER BY SALARY;


SELECT
    ( e1.first_name
      || '  '
      || e1.last_name ) AS employee,
    coalesce(concat(concat(e2.first_name, ' '), e2.last_name), 'NO MANAGER') AS manager,
    e1.job_id
FROM
    employees   e1
    LEFT JOIN employees   e2 ON e1.manager_id = e2.employee_id;
    

select * from (SELECT
    employee_id,
    first_name,
    last_name,
    salary,
    department_id,
    ROWNUM AS rn
FROM
    (
        SELECT
            employee_id,
            first_name,
            last_name,
            salary,
            department_id
        FROM
            employees
        WHERE
            department_id = 80
        ORDER BY
            salary DESC
    ))
WHERE
    rn <= 5;


select employee_id, first_name,last_name, salary, department_id, rownum as rn from employees where department_id=80 order by salary desc;


select * from employees where department_id=80 order by salary desc;


select * from (SELECT
    employee_id,
    first_name,
    last_name,
    salary,
    department_id,
    ROWNUM AS rn
FROM
    (
        SELECT
            employee_id,
            first_name,
            last_name,
            salary,
            department_id
        FROM
            employees
        WHERE
            department_id = 80
        ORDER BY
            salary DESC
    ) where rownum <= 5)
WHERE
    rn <= 5;    

