ALTER TABLE `ha_role`
ADD COLUMN `type`  int(10) NULL AFTER `status`;

ALTER TABLE `ha_user_role`
DROP COLUMN `domain`;

