```text
 #
 #   ██████╗ ██████╗ ███████╗██╗   ██╗██╗███████╗███████╗
 #  ██╔═══██╗██╔══██╗██╔════╝██║   ██║██║██╔════╝██╔════╝
 #  ██║   ██║██████╔╝█████╗  ██║   ██║██║███████╗█████╗  
 #  ██║▄▄ ██║██╔══██╗██╔══╝  ╚██╗ ██╔╝██║╚════██║██╔══╝  
 #  ╚██████╔╝██║  ██║███████╗ ╚████╔╝ ██║███████║███████╗
 #   ╚══▀▀═╝ ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝╚══════╝
 #
 #  by qbit
 #

```
<kbd>Select Language / Выберите язык</kbd>

---

<details open>
<summary><b>🇺🇸 English Documentation (Click to expand)</b></summary>
<br>

An advanced, high-performance moderation plugin for Paper and Spigot (1.16+) designed to streamline cheat inspections directly on your Minecraft server.

When a player is summoned for inspection, they are instantly teleported to a designated location, frozen, and restricted from actions. A visual bossbar countdown is displayed on their screen, and a dedicated `/contact` channel opens for communication with the moderator. Disconnecting or running out of time triggers automated punishments configured in the settings.

## Features

- **Dual Inspection Modes:** Supports dedicated Discord and AnyDesk verification modes with separate in-game chat prompt instructions.
- **Visual Feedback:** Built-in countdown timer utilizing Minecraft BossBars and screen Titles for the inspectee.
- **Isolated Communication:** Integrated `/contact` command that intercepts and routes chat exclusively between the target player and the inspecting moderator.
- **Strict Lockdown:** Complete restriction of movement, inventory interactions, item dropping, teleports, and unauthorized commands via a customizable whitelist.
- **Automated Enforcement:** Configurable auto-ban/kick execution if the player logs out during a check or fails to comply within the time limit.
- **Session Tracking:** Active session monitoring via `/revise list`.
- **Developer Extensibility:** Exposes `PlayerReviseStartEvent` for external plugin hooks and deep integrations.

## Commands

| Command | Description |
|---------|-------------|
| `/revise <player> start <discord\|anydesk>` | Initiates the inspection process under the specified mode |
| `/revise <player> finish` | Successfully concludes the inspection and releases the player |
| `/revise <player> go` | Grants additional time to the active inspection timer |
| `/revise <player> rt` | Deactivates the visible countdown timer (retains BossBar for mods) |
| `/revise list` | Displays a list of all ongoing active inspections |
| `/contact <message>` | Secure message channel between the suspect and the moderator |

## Permissions

| Permission | Description |
|------------|-------------|
| `revise.use` | Grants access to initiate, finish, and manage inspections |
| `revise.list` | Allows viewing the list of active inspections |
| `revise.anydesk` | Allows initiating inspections specifically using the AnyDesk mode |
| `revise.admin` | Permits inspecting players who possess the `revise.protect` permission |
| `revise.protect` | Protects staff members/VIPs from being summoned for inspection |
| `revise.notify` | Receives administrative notifications when a player leaves during a check |

## Dependencies

- **[Antirelog](https://www.spigotmc.org/resources/antirelog.22434/)** — Automatically removes the player from active PvP combat state when an inspection starts to prevent combat logging false positives.

## Installation & Configuration

1. Compile the source code or drop the pre-built `.jar` file into your server's `plugins/` directory.
2. Restart the server to initialize the plugin structure.
3. Modify `plugins/QRevise/config.yml` to set your spawn coordinates, timers, and punishment commands.

### Configuration Overview

Key nodes available in `config.yml`:
- `spawn-location`: Target destination for teleporting players (`world;x;y;z;yaw;pitch`).
- `revise-time` / `extra-time`: Initial countdown limit and incremental time added via `/revise go`.
- `revise-messages`: Custom instruction text blocks for Discord and AnyDesk flows.
- `player-leave` / `time-leave`: Console commands executed upon disconnect or timeout.
- `event-cancel`: Strict boolean toggles for action/command blocking during active states.

*Note: All message nodes natively support standard legacy color codes (`&`) and Hex formatting (`&x&r&r&g&g&b&b`).*

## Compilation & Development

The source architecture resides under the `dev/qbit/qrevise/` package. Building the project requires Spigot/Paper API 1.16+ and the Antirelog dependency present within your local build classpath.

## License

This project is licensed under the MIT License.

---
Developed by **qbit** · Target Environment: Minecraft 1.16+ (Paper/Spigot/Purpur)
</details>

---

<details>
<summary><b>🇷🇺 Русская документация (Нажмите, чтобы развернуть)</b></summary>
<br>

Продвинутый, высокопроизводительный плагин модерации для Paper и Spigot (1.16+), разработанный для удобного проведения проверок игроков на читы.

При вызове игрока на проверку, он мгновенно телепортируется в указанную локацию, замораживается и ограничивается в любых действиях. На его экране отображается визуальный таймер в виде боссбара и тайтла, а для связи с модератором открывается выделенный канал `/contact`. Выход с сервера или истечение времени запускают автоматические наказания, настроенные в конфигурации.

## Возможности

- **Два режима проверки:** Поддержка отдельных режимов верификации через Discord и AnyDesk с независимыми текстовыми подсказками в чате.
- **Визуальные уведомления:** Встроенный таймер обратного отсчета с использованием BossBar и полноэкранных Titles для проверяемого игрока.
- **Изолированная связь:** Команда `/contact` перехватывает и перенаправляет чат исключительно между подозреваемым и проверяющим модератором.
- **Полный локдаун:** Блокировка передвижения, взаимодействия с инвентарем, дропа предметов, телепортации и несанкционированных команд через настраиваемый белый список.
- **Автоматическое наказание:** Настраиваемое выполнение бан-команд через консоль при выходе игрока с проверки или по истечении времени таймера.
- **Мониторинг сессий:** Отслеживание всех текущих проверок в реальном времени через `/revise list`.
- **API для разработчиков:** Наличие события `PlayerReviseStartEvent` для интеграции со сторонними плагинами.

## Команды

| Команда | Описание |
|---------|----------|
| `/revise <игрок> start <discord\|anydesk>` | Начать процесс проверки в выбранном режиме |
| `/revise <игрок> finish` | Успешно завершить проверку и отпустить игрока |
| `/revise <игрок> go` | Добавить дополнительное время к текущему таймеру проверки |
| `/revise <игрок> rt` | Отключить видимый таймер (сохраняет BossBar для модераторов) |
| `/revise list` | Отобразить список всех активных проверок на сервере |
| `/contact <сообщение>` | Защищенный канал связи между игроком и модератором |

## Права (Permissions)

| Право | Описание |
|-------|----------|
| `revise.use` | Доступ к запуску, завершению и управлению проверками |
| `revise.list` | Разрешение на просмотр списка активных проверок |
| `revise.anydesk` | Разрешение на запуск проверки конкретно в режиме AnyDesk |
| `revise.admin` | Позволяет вызывать на проверку игроков с правом `revise.protect` |
| `revise.protect` | Защита администрации/VIP-игроков от вызова на проверку |
| `revise.notify` | Получение административных уведомлений, если игрок вышел во время проверки |

## Зависимости

- **[Antirelog](https://www.spigotmc.org/resources/antirelog.22434/)** — Автоматически выводит игрока из режима PvP-боя при старте проверки для предотвращения ложных срабатываний анти-лив систем.

## Установка и Настройка

1. Скомпилируйте исходный код или поместите готовый `.jar` файл в директорию `plugins/` вашего сервера.
2. Перезапустите сервер для инициализации структуры плагина.
3. Настройте координаты спавна, таймеры и команды наказания в файле `plugins/QRevise/config.yml`.

### Обзор Конфигурации

Основные узлы в `config.yml`:
- `spawn-location`: Точка телепортации проверяемых игроков (`мир;x;y;z;yaw;pitch`).
- `revise-time` / `extra-time`: Начальное время на проверку и шаг продления времени через `/revise go`.
- `revise-messages`: Кастомные блоки сообщений для сценариев Discord и AnyDesk.
- `player-leave` / `time-leave`: Консольные команды, выполняемые при выходе или таймауте.
- `event-cancel`: Жесткие переключатели (true/false) для блокировки действий и команд во время проверки.

*Примечание: Все строки сообщений поддерживают стандартные цветовые коды (`&`) и Hex-форматирование (`&x&r&r&g&g&b&b`).*

## Сборка и Разработка

Исходный код расположен в пакете `dev/qbit/qrevise/`. Для сборки проекта требуются Spigot/Paper API 1.16+ и зависимость Antirelog в локальном classpath.

## Лицензия

Этот проект распространяется под лицензией MIT.

---
Разработано: **qbit** · Среда выполнения: Minecraft 1.16+ (Paper/Spigot/Purpur)
</details>
