# Architecture Notes

## Design
- **Clean + Layered Architecture**: Controller -> Service -> Repository.
- Domain models map directly to required tables.
- Security via JWT + role-based method guards.

## ER Diagram Explanation

Core tables and relationships:
1. `users` (1..n) `user_roles` (n..1) `roles`
2. `member_profile` (1..1) `users` and optional many members to one trainer (`member_profile.trainer_id -> users.id`)
3. `trainer_profile` (1..1) `users`
4. `workout_plan` many records per member and trainer
5. `workout_session` many records per `workout_plan` and member
6. `diet_plan` many records per member/trainer
7. `attendance` many check-ins per member
8. `subscription` plan catalog; `payments` many per member + subscription
9. `progress_tracking` many logs per member (weight/BMI/body-fat trends)
10. `ai_requests_log` many logs per user for observability/auditing

## Production Notes
- Use Flyway/Liquibase for schema migration.
- Move forgot-password into tokenized email flow.
- Enforce API-level RBAC in auth service and policy layer.
