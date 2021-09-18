INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid)
VALUES (1, 'Dashboard', 'pi pi-fw pi-home', '/', 0, 0, true, uuid());

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid)
VALUES (2, 'UI Kit', 'pi pi-fw pi-star', '/ui-kit', 1, 0, true, uuid());

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid)
VALUES (3, 'Knowledge', 'pi pi-fw pi-search', '/knowledge', 1, 0, true, uuid());

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid)
VALUES (4, 'Ui-Table', 'pi pi-fw pi-id-card', '/ui-kit/ui-table', 0, 2, true, uuid());

INSERT INTO feature(id, label, icon, router_link, has_children, parent, show_in_menu, uuid)
VALUES (5, 'Ui-Input', 'pi pi-fw pi-check-square', '/ui-kit/ui-input', 0, 2, true, uuid());
