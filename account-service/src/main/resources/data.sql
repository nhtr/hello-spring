insert into group_menu(id, uuid, label, icon, order_value) values (1, uuid(), 'UI Kit', 'pi pi-fw pi-star', 1);

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid, group_id, order_value)
VALUES (1, 'Ui-Table', 'pi pi-fw pi-id-card', '/ui-kit/ui-table', 0, 0, true, uuid(), 1, 0);

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid, group_id, order_value)
VALUES (2, 'Ui-Input', 'pi pi-fw pi-check-square', '/ui-kit/ui-input', 0, 0, true, uuid(), 1, 0);

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid, group_id, order_value)
VALUES (3, 'System Config', 'pi pi-fw pi-cog', '/system-config', 0, 0, true, uuid(), NULL, 0);
